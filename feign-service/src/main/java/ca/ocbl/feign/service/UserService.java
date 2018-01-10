package ca.ocbl.feign.service;

import java.util.Collection;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.ocbl.user.entity.User;

@FeignClient(value = "user-service")
public interface UserService {

	@RequestMapping("/api/user/all") 
	public Collection<User> getUsers();

}
