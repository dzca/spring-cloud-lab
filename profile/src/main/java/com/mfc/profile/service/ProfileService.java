package com.mfc.profile.service;

import java.util.Map;

import com.mfc.profile.domain.User;

public interface ProfileService {
	public Map<String, User> getAll();

	public User get(String key);
}
