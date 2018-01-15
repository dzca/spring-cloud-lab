package ca.ocbl.auth.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.ocbl.auth.AuthApplication;
import ca.ocbl.user.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthApplication.class)
public class GithubControllerTest {

	private static Logger log = LoggerFactory.getLogger(GithubControllerTest.class);

	@Autowired
	private GithubController githubController;
	
	@Test
	public void getByEmail() throws Exception {
		User user = githubController.validateUser("AABBCC", "user");
		log.info("user=" + user.toString());
	}
}
