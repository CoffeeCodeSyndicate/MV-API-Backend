package com.coffeecodesyndicate.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.coffeecodesyndicate.api.repositories")
@EntityScan(basePackages = "com.coffeecodesyndicate.api.models")
public class MvApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvApiApplication.class, args);
	}

}
