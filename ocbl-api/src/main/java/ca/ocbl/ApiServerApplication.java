package ca.ocbl;

import java.util.Arrays;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ca.ocbl.common.domain.User;
import ca.ocbl.common.enums.UserStatuEnum;
import ca.ocbl.user.dao.UserRepository;

@SpringBootApplication
public class ApiServerApplication {

	private static Logger log = LoggerFactory.getLogger(ApiServerApplication.class);

	@Autowired
	DataSource dataSource;
	

	public static void main(String[] args) {
		SpringApplication.run(ApiServerApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		log.info("init() DATASOURCE = " + dataSource);
		return (evt) -> Arrays.asList(
				"dustin,lana,luke"
						.split(",")).forEach(name -> {
			User user = userRepository.save(new User(name, name + "@abc.ca", UserStatuEnum.REGISTERED));
		});
	}
	
//	@Bean
//	public CommandLineRunner demo() {
//		return (args) -> {
//			log.info("init() DATASOURCE = " + dataSource);
//		};
//	}
	
	//implements CommandLineRunner
//	@Override
//	public void run(String... args) throws Exception {
//		log.info("init() DATASOURCE = " + dataSource);
//	}

}
