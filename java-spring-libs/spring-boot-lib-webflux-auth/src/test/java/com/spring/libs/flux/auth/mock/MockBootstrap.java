package com.spring.libs.flux.auth.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.spring.libs.flux.auth")
public class MockBootstrap {
	public static void main(String[] args) {
		SpringApplication.run(MockBootstrap.class, args);
	}
}

