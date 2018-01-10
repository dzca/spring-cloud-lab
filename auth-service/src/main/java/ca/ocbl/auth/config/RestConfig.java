package ca.ocbl.auth.config;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Spring rest template configuration
 * @author dike
 */
@Configuration
public class RestConfig {
	
	@Value("${application.rest.github_host}")
	private String githubHost;
	
	public String getGithubHost() {
		return githubHost;
	}

	public void setGithubHost(String githubHost) {
		this.githubHost = githubHost;
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(5000);// ms
		factory.setConnectTimeout(15000);// ms
		return factory;
	}

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		RestTemplate template = new RestTemplate(factory);
		template.getMessageConverters().add(0,
				new StringHttpMessageConverter(Charset.forName("utf-8")));
		return template;
	}
}
