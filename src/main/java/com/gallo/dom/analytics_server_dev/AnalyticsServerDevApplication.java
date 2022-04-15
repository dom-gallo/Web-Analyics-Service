package com.gallo.dom.analytics_server_dev;

import com.gallo.dom.analytics_server_dev.model.Domain;
import com.gallo.dom.analytics_server_dev.model.User;
//import com.gallo.dom.analytics_server_dev.repository.DomainRepository;
import com.gallo.dom.analytics_server_dev.repository.DomainRepository;
import com.gallo.dom.analytics_server_dev.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootApplication
public class AnalyticsServerDevApplication {
	// LOGGER
	private static Logger LOGGER = LoggerFactory.getLogger(AnalyticsServerDevApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsServerDevApplication.class, args);
	}

	// Testing for persistence layer
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, DomainRepository domainRepository) {
		return args -> {

			User myUser = new User("dgallo519@gmail.com", "password", LocalDateTime.now());

			userRepository.save(myUser);

		};
	}
}
