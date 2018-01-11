package ca.ocbl.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class SystemSettings {
	@Value("${hosts.github}")
	private String githubHost;
	
	@Value("${hosts.web}")
	private String webHost;
	
	@Value("${hosts.user}")
	private String userHost;
	
	@Value("${github.client_id}")
	private String githubClientId;
	
	@Value("${github.client_secret}")
	private String githubClientSecret;
	
	@Value("${github.scpoe}")
	private String githubScope;
	
	public String getGithubHost() {
		return githubHost;
	}

	public void setGithubHost(String githubHost) {
		this.githubHost = githubHost;
	}

	public String getUserHost() {
		return userHost;
	}

	public void setUserHost(String userHost) {
		this.userHost = userHost;
	}

	public String getWebHost() {
		return webHost;
	}

	public void setWebHost(String webHost) {
		this.webHost = webHost;
	}

	public String getGithubClientId() {
		return githubClientId;
	}

	public void setGithubClientId(String githubClientId) {
		this.githubClientId = githubClientId;
	}

	public String getGithubClientSecret() {
		return githubClientSecret;
	}

	public void setGithubClientSecret(String githubClientSecret) {
		this.githubClientSecret = githubClientSecret;
	}

	public String getGithubScope() {
		return githubScope;
	}

	public void setGithubScope(String githubScope) {
		this.githubScope = githubScope;
	}
}
