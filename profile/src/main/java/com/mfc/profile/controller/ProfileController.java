package com.mfc.profile.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mfc.profile.dao.ProfileRepository;
import com.mfc.profile.domain.User;
import com.mfc.profile.service.ProfileService;

@RestController
public class ProfileController {

	@Autowired
	ProfileService profileService;
	
	@RequestMapping("/{key}")
	public User getProfile(@PathVariable String key) {
		return profileService.get(key);
	}
	
	@RequestMapping("/")
	public Map<String, User>  getAll() {
		return profileService.getAll();
	}
}
