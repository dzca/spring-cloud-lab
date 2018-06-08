package ca.ocbl.user.api;

import java.util.List;

import org.junit.Assert;
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
import ca.ocbl.user.entity.User;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserControllerTest {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserController userController;
	
	private MockMvc mvc;
	
	@Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
	
	@Test
	public void getAll() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders
                .get("/api/user/all")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mvc.perform(builder).andReturn();
        
        String jsonString = result.getResponse().getContentAsString();
        
        log.info("get all users, json={}",jsonString);
        
        List<User> users = JSON.parseArray(jsonString, User.class);
        
        Assert.assertEquals("users size should be 3", 3, users.size());
        for(User u:users) {
        	System.out.println(u.toString());
        }
        
	}
}
