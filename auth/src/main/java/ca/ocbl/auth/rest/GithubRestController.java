package ca.ocbl.auth.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import ca.ocbl.api.UriMapping;
import ca.ocbl.auth.config.SystemSettings;
import ca.ocbl.auth.service.TokenService;
import ca.ocbl.auth.service.UserService;
import ca.ocbl.common.UserStatuEnum;
import ca.ocbl.user.entity.User;

@RestController
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

	@RequestMapping(value = "/hi")
	public String home() {
		log.debug("in hi()");
		return "hi, i am user :" + systemSettings.getSckUser();
	}
	
	/**
	 * check if the access_token is in redis
	 * 
	 * user could be null, could be registered(approved)=r, unregistered=u, free(not approved yet)=f.
	 * @return
	 */
	@RequestMapping(value = "/access_token/{token}")
	public User getUser(@PathVariable("token") String token) {
		log.debug("calling accessToken");
		User user = tokenService.getUser(token);
		if (user != null) {
			tokenService.resetExpireTime(token);
		}
		log.debug("calling accessToken, return user={}", user);
		return user;
	}


}
