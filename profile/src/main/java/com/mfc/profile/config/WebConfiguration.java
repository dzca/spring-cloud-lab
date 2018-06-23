package com.mfc.profile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mfc.profile.interceptors.TokenInterceptor;


@Profile("!mock")
@EnableWebMvc
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	@Bean
	TokenInterceptor getTokenInterceptor() {
		return new TokenInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getTokenInterceptor()).addPathPatterns("/**");
	}
}
