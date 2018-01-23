package ca.ocbl.redis.service;

import ca.ocbl.common.domain.User;

public interface TokenService {
	public User getUser(String key);
	
	public void saveToken(String key, User user);
	
	public void resetExpireTime(String token);
}
