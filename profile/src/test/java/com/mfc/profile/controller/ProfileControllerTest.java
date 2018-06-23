package com.mfc.profile.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.mfc.profile.domain.User;
import com.mfc.profile.service.ProfileService;

@ActiveProfiles("mock")
@RunWith(SpringRunner.class)
@SpringBootTest  
//controller only -@WebMvcTest
@AutoConfigureMockMvc
public class ProfileControllerTest {

	@InjectMocks
	private ProfileController controller;

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private ProfileService service;

	public Map<String, String> getCommonParamsHeaders() {
		Map<String, String> headers = new HashMap<>();

		headers.put("source_system_ID", "CLIENT_MOBILE");
		headers.put("application", "Test");
		headers.put("language", "English");
		headers.put("originating_IP", "127.0.0.1");
		headers.put("device_type", "iphone");
		headers.put("carrier", "fido");
		headers.put("geoLocation_latitude", "null");
		headers.put("geoLocation_longitude", "null");

		return headers;
	}
	
	public HttpHeaders getHttpHeaders() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Authorization","AABB");
		requestHeaders.setAll(getCommonParamsHeaders());
		return requestHeaders;
	}
	
	@Test
	public void testLookUp() throws Exception {
		 when(service.get("/account2")).thenReturn(new User("account3", "Dustin"));

		this.mockMvc.perform(get("/account2").headers(getHttpHeaders()))
				 .andDo(MockMvcResultHandlers.print())
				 .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()));
	}
}
