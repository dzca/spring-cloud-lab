package ca.ocbl.auth.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import ca.ocbl.api.UriMapping;
import ca.ocbl.auth.config.SystemSettings;
import ca.ocbl.auth.rest.RestHelper;
import ca.ocbl.auth.service.TokenService;
import ca.ocbl.auth.service.UserService;
import ca.ocbl.user.entity.User;

@Controller
@RequestMapping("/api/github")
public class GithubController {

	private static Logger log = LoggerFactory.getLogger(GithubController.class);

	@Autowired
	private RestHelper restHelper;
	
	@Autowired
	private SystemSettings systemSettings;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	UserService userService;
	
	/**
	 * Test: http://localhost:5003/rest/github/callback?code=xya
	 * 
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/access_token", method = RequestMethod.GET)
	public String callback(@RequestParam("code") String code) {
		
		String githubHost = systemSettings.getGithubHost();
		String clientId = systemSettings.getGithubClientId();
		String secret = systemSettings.getGithubClientSecret();
		String scope = systemSettings.getGithubScope();
		
		String path = UriMapping.GITHUB_ACCESS_TOKEN;
		String url = githubHost + path;
		
		url = url.format(url, clientId, secret, code);
		
		// query access_token form github with code
		String result = restHelper.post(url, "");
		log.info("github callback called, result = {}", result);
		
		String accessToken = JSON.parseObject(result).getString("access_token");
		
		if(StringUtils.isEmpty(accessToken)) {
			log.error("github callback does not return valid token, code = {}", code);
			return null;
		}
		
		User user = validateUser(accessToken, scope);
		
		String redirectUrl = systemSettings.getWebHost();
		if(user == null) {
			// redirect to UI_REGISTER PAGE
			redirectUrl += UriMapping.UI_REGISTER;
		} else {
			// populate user to redis
			tokenService.saveToken(accessToken, user);
			// redirect to UI_GITHUB_LOGIN PAGE
			redirectUrl += UriMapping.UI_GITHUB_LOGIN;
		}
		redirectUrl= redirectUrl.format(redirectUrl, accessToken);
		return "redirect:" + redirectUrl;
	}
	
	/**
	 * register token:user in redis server
	 * https://api.github.com/user?access_token=AABBCC&scope=user:email
	 * @param token
	 * @return
	 */
	public User validateUser(String token, String scope){
		
		// query user email form github service
		String url = String.format(systemSettings.getGithubHost() + UriMapping.GITHUB_USER, token, scope);
		
		// {'email': 'anna@abc.ca'}
		String emailJsonString = restHelper.get(url);
		log.debug("getting email form github.com  emailJsonString={}",emailJsonString);
		
		if(StringUtils.isEmpty(emailJsonString)) {
			log.info("invalid token in github.");
			return null;
		}
		
		String email = JSON.parseObject(emailJsonString).getString("email");
		
		// query user-service to load the user from database
//		String userHost = systemSettings.getUserHost();
//		String userUrl = userHost + UriMapping.USER_REST_FIND_BY_EMAIL;
		
		User user = userService.getUserByEmail(email);
		log.debug("auth call user service, return user={}", user);
//		String userJsonString = restHelper.post(url, email);
//		
//		// convert string to User object
//		if(StringUtils.isEmpty(userJsonString)) {
//			log.info("user is not registered in the user-service database");
//			return null;
//		}
//		
//		User user = JSON.parseObject(userJsonString, User.class);
		
		return user;
	}
}
