package ca.ocbl.auth.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.ocbl.api.UriMapping;
import ca.ocbl.auth.config.RestConfig;
import ca.ocbl.auth.rest.RestHelper;
import ca.ocbl.user.entity.User;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/rest/github")
public class GithubController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private RestHelper restHelper;
	
	@Autowired
	private RestConfig restConfig;
	
	/**
	 * Test: http://localhost:3002/rest/github/callback?code=xyz
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	public String callback(@RequestParam("code") String code) {

		String path = UriMapping.GITHUB_ACCESS_TOKEN;
		path += "?client_id=yyy";
		path += "&client_secret=xxx";
		path += "&code=" + code;
		
		String url = restConfig.getGithubHost() + path;
		
		String result = restHelper.post(url, "");
		
		log.info("callback called, result = {}", result);
		
		String accessToken = JSON.parseObject(result).getString("access_token");

		String redirectUrl = "http://localhost:8080/login/github/" + accessToken;
		return "redirect:" + redirectUrl;
	}
	
	/**
	 * https://api.github.com/user?access_token=AABBCC&scope=user:email
	 * @param token
	 * @return
	 */
	private User getUser(String token, String scope){
		String url = String.format(restConfig.getGithubHost() + UriMapping.GITHUB_USER, token, scope);
		
		String result = restHelper.get(url);
		String email = JSON.parseObject(result).getString("email");
		
		// query user-service to load the user info by email
		
		return null;
	}
}
