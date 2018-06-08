package com.mfc.profile.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.stereotype.Repository;

import com.mfc.profile.domain.User;

@Repository
public class ProfileRepository {

	private Map<String, User> users = new HashMap<String, User>();

	public ProfileRepository() {
		IntStream.range(1, 11).forEach(item -> add(item));
	}

	private void add(int i) {
		String key = "account" + i;
		User user = new User(key, "name" + i);
		users.put(key, user);
	}
	
	public Map<String, User> getAll() {
		return users;
	}
	
	public User get(String key) {
		return users.get(key);
	}
}
