package com.mq.kafka.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.mq.kafka.demo"})
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
    }
}
