package ca.ocbl.auth.service;

import ca.ocbl.user.entity.User;

public interface TokenService {
	public User getUser(String key);
	
	public void saveToken(String key, User user);
	
	public void resetExpireTime(String token);
}
