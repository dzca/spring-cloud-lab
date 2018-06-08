package com.mfc.profile.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mfc.profile.dao.ProfileRepository;
import com.mfc.profile.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceTest {

	@Mock
	ProfileRepository mockProfileRepository;

	@InjectMocks
	ProfileServiceImpl mockProfileService;
	
	@Test
	public void get() {
		when(mockProfileRepository.get("account3")).thenReturn(new User("account4","name4"));
		User expected = new User("account4","name4");
		assertEquals(expected.getName(), mockProfileService.get("account3").getName());
		assertEquals(expected, mockProfileService.get("account3"));
	}
}
