package ca.ocbl.common;

public class Constants {

	/**
	 * HTTP header Authorization
	 */
	public static final String AUTHORIZATION = "authorization";

	public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
	
	public static final String REDIS_TOKENS_PREFIX = "auth:github:token";
	
	public static final int TOKEN_EXPIRES_MINUTES = 60;
}
