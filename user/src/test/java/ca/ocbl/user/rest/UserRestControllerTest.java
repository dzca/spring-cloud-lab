package ca.ocbl.user.rest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alibaba.fastjson.JSON;

import ca.ocbl.user.UserApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserRestControllerTest {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRestController userRestController;
	
	private MockMvc mvc;
	
	@Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }
	
	@Test
	public void getByEmail() throws Exception {
		
		Map<String, String> email = new HashMap<String, String>();  
		email.put("email", "dustin@abc.ca");  
		String json = JSON.toJSONString(email);
		log.info("get user by email, request email json={}", json);
		
		RequestBuilder builder = MockMvcRequestBuilders
                .post("/rest/user/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("dustin@abc.ca");
 
        MvcResult result = mvc.perform(builder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        
//        RequestBuilder builder = MockMvcRequestBuilders
//                .get("/api/user/all")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON_UTF8);
//        MvcResult result = mvc.perform(builder).andReturn();
//        
//        String jsonString = result.getResponse().getContentAsString();
//        
//        log.info("get all users, json={}",jsonString);
//        
//        List<User> users = JSON.parseArray(jsonString, User.class);
//        
//        Assert.assertEquals("users size should be 5", 5, users.size());
//        for(User u:users) {
//        	System.out.println(u.toString());
//        }
        
	}
}
