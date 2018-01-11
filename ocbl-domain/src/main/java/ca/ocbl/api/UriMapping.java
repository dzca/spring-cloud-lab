package ca.ocbl.api;

/**
 * service URL
 *
 */
public interface UriMapping {

	// ================================
	// github oauth API
	// ================================
	String GITHUB_ACCESS_TOKEN = "/api/access_token?client_id=%s&client_secret=%s&code=%s";

	String GITHUB_USER = "/api/user?access_token=%s&scope=%s";
	
	String GITHUB_LOGIN = "/api/login/%s";
	
	// ================================
	// web UI 
	// ================================
	String UI_GITHUB_LOGIN="/login/github/%s";
	
	String UI_LOGIN_FAILED="/login/error/%s";
	
	String UI_REGISTER="/login/register";
	
	// ================================
	// authorization service 
	// ================================
	String AUTH_GITHUB_CALLBACK = "/api/github/callback";
	
	// ================================
	// user service 
	// ================================
	
	String USER_API_ALL="/api/user/all";
	
	String USER_REST_FIND_BY_EMAIL="/rest/user/";
}
