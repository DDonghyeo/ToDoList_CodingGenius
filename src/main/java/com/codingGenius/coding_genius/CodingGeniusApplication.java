package com.codingGenius.coding_genius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableMongoRepositories
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CodingGeniusApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingGeniusApplication.class, args);
	}

}
