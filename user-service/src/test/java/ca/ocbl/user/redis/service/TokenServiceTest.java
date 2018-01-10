package ca.ocbl.user.redis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import ca.ocbl.user.Application;
import ca.ocbl.user.config.RedisConfig;
import ca.ocbl.user.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
//@ComponentScan({
//    "ca.ocbl.user.entity",
//    "ca.ocbl.user.redis"
//})
@ContextConfiguration(classes = RedisConfig.class)
public class TokenServiceTest {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TokenService tokenService;
		
	@Test
	public void testSet() throws Exception {
		User user = new User();
		user.setEmail("dustin@abc.com");
		
		tokenService.saveToken("AABBCC", user);
		
		
		User u = tokenService.getUser("AABBCC");
		log.info("got user email={}", u.getEmail());
//		logger.info("The user name is {}", user.getUserName());
//		Assert.assertEquals("username not match", "dustin", user.getUserName());
	}
	
	@Test
	public void tokenExists(){
		User u = tokenService.getUser("AABBCCX");
		log.info("got user email={}", u);
	}
}
