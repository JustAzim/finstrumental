package com.example.finstrumental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FinstrumentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinstrumentalApplication.class, args);
	}

}
