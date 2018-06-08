package com.mfc.profile.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mfc.profile.domain.User;
import com.mfc.profile.service.ProfileService;

// http://www.springboottutorial.com/spring-boot-unit-testing-and-mocking-with-mockito-and-junit
// https://spring.io/guides/gs/testing-web/
// http://www.baeldung.com/injecting-mocks-in-spring
// http://www.baeldung.com/spring-boot-testing

@RunWith(SpringRunner.class)
@SpringBootTest // @WebMvcTest
@AutoConfigureMockMvc
public class ProfileControllerTest {
	@Autowired
	private ProfileController controller;

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Autowired
	private MockMvc mockMvc;

//	@MockBean
//	private ProfileService service;

	@Test
	public void testLookUp() throws Exception {
		// when(service.get("/account2")).thenReturn(new User("account3", "Dustin"));

		this.mockMvc.perform(get("/account2"))
				// .andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.name", is("name2")));
	}
}
