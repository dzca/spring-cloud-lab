package ca.ocbl.user.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.ocbl.user.config.SystemSettings;
import ca.ocbl.user.dao.UserRepository;
import ca.ocbl.user.entity.User;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private SystemSettings systemSettings;

	/**
	 * fetch users by email POST http://localhost:8901/rest/user/
	 * 
	 * @return
	 */
	@RequestMapping(value = "/email/", method = RequestMethod.POST, produces = "application/json")
	public User getUserByEmail(@RequestBody String email) {
		log.info("getUserByEmail() email={}", email);
		return userRepository.findByEmail(email);
	}

//	@RequestMapping("/hello")
//	public String showUser() {
//		log.info("showUser() user={}", systemSettings.getSckUser());
//		String sb = "username is " + systemSettings.getSckUser();
//		return sb;
//	}

	// @RequestMapping(value="/login/:token", method = RequestMethod.POST, produces
	// = "application/json")
	// public RestResult saveUser(@RequestBody User user) {
	//
	// return null;
	// }
}
