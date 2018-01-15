package ca.ocbl.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
public class SystemSettings {
	@Value("${sck.user}")
	private String sckUser;

	public String getSckUser() {
		return sckUser;
	}

	public void setSckUser(String sckUser) {
		this.sckUser = sckUser;
	}
}
