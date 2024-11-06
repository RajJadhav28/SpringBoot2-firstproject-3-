package com.example.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Firstsproject3Application {

	public static void main(String[] args) {
		SpringApplication.run(Firstsproject3Application.class, args);
	}

}
