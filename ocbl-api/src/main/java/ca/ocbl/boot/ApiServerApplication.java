package ca.ocbl.boot;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ca.ocbl.common.domain.User;
import ca.ocbl.common.enums.UserStatuEnum;
import ca.ocbl.user.dao.UserRepository;

@SpringBootApplication
public class ApiServerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ApiServerApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return (evt) -> Arrays.asList(
				"dustin,lana,luke"
						.split(",")).forEach(name -> {
			User user = userRepository.save(new User(name, name + "@abc.ca", UserStatuEnum.REGISTERED));
		});
	}
}
