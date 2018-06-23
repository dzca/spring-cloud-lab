package com.mfc.profile.mocks;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mfc.profile.dao.ProfileRepository;
import com.mfc.profile.domain.User;
import com.mfc.profile.service.ProfileService;

@Service
@Profile("mock")
public class MockProfileService implements ProfileService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProfileRepository dao;
	
	public Map<String, User> getAll() {
		return dao.getAll();
	}
	
	public User get(String key) {
		System.out.println("====> service.get() called");
		logger.debug("====> service.get() called");
		return dao.get(key);
	}

}
