package ca.ocbl.api;

/**
 * service URL
 *
 */
public interface UriMapping {

	// ================================
	// github oauth API
	// ================================
	String GITHUB_ACCESS_TOKEN = "/api/access_token";

	String GITHUB_USER = "/api/user?access_token=%s&scope=%s";
	
	String GITHUB_LOGIN = "/api/login/%s";
	
	// ================================
	// authorization service 
	// ================================
	String AUTH_GITHUB_CALLBACK = "/api/github/callback";
	
	// ================================
	// user service 
	// ================================
	
	String USER_API_ALL="/api/users/all";
	
	String USER_REST_FIND_BY_EMAIL="/rest/users";
}
