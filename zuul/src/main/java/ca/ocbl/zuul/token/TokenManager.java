package ca.ocbl.zuul.token;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.ocbl.user.entity.User;

@Component
public class TokenManager {
	
	@Autowired
	private TokenService tokenService;
	
	/**
	 * authorization = 'Bear GITHUB_XXYYZZ'
	 * @param authorization
	 * @return
	 */
	public String parseToken(String authorization) {
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
        
        return token;
	}
	
	public User getUserByToken(String token) {
        User user = tokenService.getUser(token);
        
        if(user!= null){
        	tokenService.resetExpireTime(token);
        }
        
        return user;
    }
}
