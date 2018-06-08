package ca.ocbl.user;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import ca.ocbl.common.UserStatuEnum;
import ca.ocbl.user.dao.UserRepository;
import ca.ocbl.user.entity.User;

@SpringBootApplication
@EnableEurekaClient
public class UserApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
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
