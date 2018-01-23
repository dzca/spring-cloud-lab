package ca.ocbl.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ca.ocbl.common.domain.User;
import ca.ocbl.redis.service.TokenService;

@Component
public class TokenManager {
	
	@Autowired
	private TokenService tokenService;
	
	public User checkToken(String authorization) {
        if (StringUtils.isEmpty(authorization)) {
            return null;
        }
        
        String[] param = authorization.split(" ");
        if (param.length != 2) {
            return null;
        }
        
        String token = param[1];
        
        if (StringUtils.isEmpty(token)){
        	return null;
        }
        
        User user = tokenService.getUser(token);
        
        if(user!= null){
        	tokenService.resetExpireTime(token);
        }
        
        return user;
    }
}
