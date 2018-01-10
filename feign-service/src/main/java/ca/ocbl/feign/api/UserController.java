package ca.ocbl.feign.api;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.ocbl.feign.service.UserService;
import ca.ocbl.user.entity.User;

/**
 * All private user API exposed to public domain
 * @author dike
 *
 */
@RestController
public class UserController {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserService userService;
	
	/**
	 * list all users
	 * http://localhost:8901/api/users/
	 * @return
	 */
	@RequestMapping("/api/user/allxx") 
	public Collection<User> getUsers() {
		
		return userService.getUsers();
	}

}


