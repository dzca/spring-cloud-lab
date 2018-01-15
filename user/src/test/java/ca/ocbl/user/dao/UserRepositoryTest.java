package ca.ocbl.user.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.ocbl.user.UserApplication;
import ca.ocbl.user.entity.User;

// @RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void setUp() {
		logger.info("set up database ");
	}
	
	@Test
	public void testGet() throws Exception {
		List<User> users = userRepository.findAll();
		for(User user : users){
			logger.info("username = {}", user.getUserName());
		}
		
//		logger.info("The user name is {}", user.getUserName());
//		Assert.assertEquals("username not match", "dustin", user.getUserName());
	}
}
