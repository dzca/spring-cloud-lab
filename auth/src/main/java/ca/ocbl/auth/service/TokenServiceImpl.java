package ca.ocbl.auth.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import ca.ocbl.common.Constants;
import ca.ocbl.user.entity.User;

@Service
public class TokenServiceImpl implements TokenService {

	private static Logger log = LoggerFactory.getLogger(TokenServiceImpl.class);
	
	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	public User getUser(String token) {
		return (User) redisTemplate.opsForValue().get(
				Constants.REDIS_TOKENS_PREFIX + token);
	}

	public void saveToken(String token, User user){
		log.debug("redis template save token={}, user={}", Constants.REDIS_TOKENS_PREFIX + token, user);
		redisTemplate.opsForValue().set(Constants.REDIS_TOKENS_PREFIX + token, user);
		redisTemplate.expire(Constants.REDIS_TOKENS_PREFIX + token, Constants.TOKEN_EXPIRES_MINUTES, TimeUnit.MINUTES);
	}
	
	public void resetExpireTime(String token){
		redisTemplate.expire(Constants.REDIS_TOKENS_PREFIX + token, Constants.TOKEN_EXPIRES_MINUTES, TimeUnit.MINUTES);
	}
	
	public void deleteToken(String token){
		redisTemplate.delete(Constants.REDIS_TOKENS_PREFIX + token);
	}
}
