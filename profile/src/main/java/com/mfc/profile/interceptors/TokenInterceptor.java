package com.mfc.profile.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// consider implements HandlerInterceptor
public class TokenInterceptor extends HandlerInterceptorAdapter {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String authorization = request.getHeader("Authorization");
		logger.error("authorization = {}", authorization);
		if(authorization != "AABB") {
			response.addHeader("Content-Type", "application/json");
		    response.setStatus(HttpStatus.UNAUTHORIZED.value());
		    return false;
		}
	
		return true;
	}
}
