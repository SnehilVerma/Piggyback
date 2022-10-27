package com.fivetwenty.piggyback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PiggybackApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiggybackApplication.class, args);
		System.out.println("Starting Application");
	}

}
