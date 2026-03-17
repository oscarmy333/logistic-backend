package com.marvisa.logistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LogisticApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticApplication.class, args);
	}

}
