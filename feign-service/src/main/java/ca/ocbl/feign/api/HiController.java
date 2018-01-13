package ca.ocbl.feign.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.ocbl.feign.service.HiService;

@RestController
public class HiController {

	private static Logger log = LoggerFactory.getLogger(HiController.class);
	
	@Autowired
	HiService hiService;
	
	@RequestMapping("/api/hi") 
	public String getUsers() {
		log.info("in hicontroller");
		return hiService.hi();
	}

}


