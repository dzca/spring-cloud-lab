package ca.ocbl.auth.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ocbl.user.entity.User;

@FeignClient(value = "user")
public interface UserService {

	@RequestMapping(value = "/rest/user/email/", method = RequestMethod.POST, produces = "application/json")
	public User getUserByEmail(@RequestBody String email);
}
