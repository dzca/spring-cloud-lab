package ca.ocbl.user.api;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.ocbl.common.domain.User;
import ca.ocbl.user.dao.UserRepository;

/**
 * All private user API exposed to public domain
 * @author dike
 *
 */
@RestController
@RequestMapping("/apix/userx")
public class UserController {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * list all users
	 * http://localhost:8901/api/users/
	 * @return
	 */
	@RequestMapping(value="/all", method = RequestMethod.GET, produces = "application/json") 
	public Collection<User> getUsers() {
		log.info("getUsers() called");
		return userRepository.findAll();
	}

}


