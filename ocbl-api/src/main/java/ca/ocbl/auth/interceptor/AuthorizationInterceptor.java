package ca.ocbl.auth.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ca.ocbl.auth.TokenManager;
import ca.ocbl.common.Constants;
import ca.ocbl.common.domain.User;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	private static Logger log = LoggerFactory.getLogger(AuthorizationInterceptor.class);
	
	@Autowired
	private TokenManager manager;
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String path = request.getRequestURI().toString();
		log.info("Getting context path={}", path);
		
		// if (path.contains("/rest/")) {
		if(path.matches("\\/rest\\/[\\w]+[\\/]?")) {
			return true;
		}
		
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		// parse token from HTTP header
		String authorization = request.getHeader(Constants.AUTHORIZATION);
		log.info("Getting authorization string={}", authorization);
		
		User user = manager.checkToken(authorization);
		log.info("Getting authorization user={}", user);
		if(user == null || StringUtils.isEmpty(user.getEmail())){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		
		request.setAttribute(Constants.CURRENT_USER_ID, user.getEmail());
		return true;
	}
	
	
}
