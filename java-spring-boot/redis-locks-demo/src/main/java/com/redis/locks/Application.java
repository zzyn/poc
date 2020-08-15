package com.edtech.redis.locks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(Application.class, args);

	}



}
