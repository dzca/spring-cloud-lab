package ca.ocbl.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
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
