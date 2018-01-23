package ca.ocbl.user.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import ca.ocbl.ApiServerApplication;
import ca.ocbl.common.domain.User;
import ca.ocbl.common.enums.UserStatuEnum;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiServerApplication.class)
@DataJpaTest
@Profile("dev")
public class UserRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestEntityManager entityManager;
    
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void setUp() {
		logger.info("set up database ");
	}
	
	@Test
	public void testGet() throws Exception {
		User u1 = new User("lana","lana@abc,ca", UserStatuEnum.REGISTERED);
		entityManager.persist(u1);
		
		Iterable<User> users = userRepository.findAll();
		for(User user : users){
			logger.info("username = {}", user.getUserName());
		}
		
//		logger.info("The user name is {}", user.getUserName());
//		Assert.assertEquals("username not match", "dustin", user.getUserName());
	}
}
