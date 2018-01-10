package ca.ocbl.user;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import ca.ocbl.user.dao.UserRepository;
import ca.ocbl.user.entity.User;

@SpringBootApplication
@EnableEurekaClient
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return (evt) -> Arrays.asList(
				"dustin,lana,luke,anna,martin"
						.split(",")).forEach(name -> {
			User user = userRepository.save(new User(name, name + "@abc.ca"));
		});
	}
}
