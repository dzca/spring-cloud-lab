package ca.ocbl.user.utils;

import org.junit.Test;

public class RegexTest {
	// private Logger log = LoggerFactory.getLogger(getClass());
	@Test
	public void context() {
		String path="/rest/users/";
		if(path.matches("\\/rest\\/[\\w]+[\\/]?")) {
			System.out.println("match");
		} else {
			System.out.println("no match");
		}
	}
	
	@Test
	public void context_without_slash() {
		String path="/rest/users";
		if(path.matches("\\/rest\\/[\\w]+[\\/]?")) {
			System.out.println("match");
		} else {
			System.out.println("no match");
		}
	}
	
	@Test
	public void contains_rest() {
		String path="/user/xx/rest/user/aa/";
		if(path.contains("/rest/")) {
			System.out.println("rest match");
		} else {
			System.out.println("rest no match ");
		}
	}
}
