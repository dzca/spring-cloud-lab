package com.mfc.profile.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfc.profile.dao.ProfileRepository;
import com.mfc.profile.domain.User;

@Service
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	ProfileRepository dao;
	
	public Map<String, User> getAll() {
		return dao.getAll();
	}
	
	public User get(String key) {
		return dao.get(key);
	}
}
