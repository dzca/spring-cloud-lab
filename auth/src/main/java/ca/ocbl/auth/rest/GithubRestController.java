package ca.ocbl.auth.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import ca.ocbl.api.UriMapping;
import ca.ocbl.auth.config.SystemSettings;
import ca.ocbl.auth.service.TokenService;
import ca.ocbl.auth.service.UserService;
import ca.ocbl.common.UserStatuEnum;
import ca.ocbl.user.entity.User;

@Controller
@RequestMapping("/rest/github")
public class GithubRestController {

	private static Logger log = LoggerFactory.getLogger(GithubRestController.class);

	@Autowired
	private RestHelper restHelper;

	@Autowired
	private SystemSettings systemSettings;

	@Autowired
	private TokenService tokenService;

	@Autowired
	UserService userService;

	@RequestMapping("/rest/hi")
	public String home() {
		log.info("in hi()");
		return "hi, i am user :" + systemSettings.getSckUser();
	}
	
	/**
	 * check if the access_token is in redis
	 * 
	 * user could be null, could be registered(approved)=r, unregistered=u, free(not approved yet)=f.
	 * @return
	 */
	@RequestMapping(value = "/access_token", method = RequestMethod.GET, produces = "application/json")
	public User getUser(@RequestParam("token") String token) {
		log.debug("calling accessToken");
		User user = tokenService.getUser(token);
		if (user != null) {
			tokenService.resetExpireTime(token);
		}
		return user;
	}

	/**
	 * Test: http://localhost:5003/rest/github/callback?code=xya
	 * 
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	public String callback(@RequestParam("code") String code) {

		String githubHost = systemSettings.getGithubHost();
		String clientId = systemSettings.getGithubClientId();
		String secret = systemSettings.getGithubClientSecret();
		String scope = systemSettings.getGithubScope();

		String path = UriMapping.GITHUB_ACCESS_TOKEN;
		String url = githubHost + path;

		url = String.format(url, clientId, secret, code);

		// query access_token form github with code
		String result = restHelper.post(url, "");
		log.info("github callback called, result = {}", result);

		String accessToken = JSON.parseObject(result).getString("access_token");

		if (StringUtils.isEmpty(accessToken)) {
			log.error("github callback does not return valid token, code = {}", code);
			return null;
		}

		User user = validateUser(accessToken, scope);

		String redirectUrl = systemSettings.getWebHost();
		// populate user to redis
		tokenService.saveToken(accessToken, user);
		redirectUrl += UriMapping.UI_GITHUB_LOGIN;

		redirectUrl = String.format(redirectUrl, accessToken);
		return "redirect:" + redirectUrl;
	}

	/**
	 * register token:user in redis server
	 * https://api.github.com/user?access_token=AABBCC&scope=user:email
	 * 
	 * @param token
	 * @return
	 */
	public User validateUser(String token, String scope) {

		// query user email form github service
		String url = String.format(systemSettings.getGithubHost() + UriMapping.GITHUB_USER, token, scope);

		// {'email': 'anna@abc.ca'}
		String emailJsonString = restHelper.get(url);
		log.debug("getting email form github.com  emailJsonString={}", emailJsonString);

		if (StringUtils.isEmpty(emailJsonString)) {
			log.info("invalid token in github.");
			return null;
		}

		String email = JSON.parseObject(emailJsonString).getString("email");

		User user = userService.getUserByEmail(email);
		log.debug("auth call user service, return user={}", user);

		// if user is not registered, we create a new user with state=unregistered
		if(user==null) {
			user = new User();
			user.setEmail(email);
			user.setStatus(UserStatuEnum.UNREGISTERED);
		}

		return user;
	}
}
