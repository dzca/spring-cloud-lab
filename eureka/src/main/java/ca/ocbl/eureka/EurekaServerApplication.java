package ca.ocbl.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

	private static Logger log = LoggerFactory.getLogger(EurekaServerApplication.class);
	
	
	public static void main(String[] args) {
		log.info("Eureka started");
		log.debug("Eureka started");
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
