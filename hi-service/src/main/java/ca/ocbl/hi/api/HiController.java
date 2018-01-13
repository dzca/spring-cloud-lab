package ca.ocbl.hi.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
	
	private static Logger log = LoggerFactory.getLogger(HiController.class);
	
	@Autowired
	private SystemSettings systemSettings;
	
	@RequestMapping("/hi")
	public String home() {
		log.info("in hi()");
		return "hi, i am user :" + systemSettings.getSckUser();
	}
}
