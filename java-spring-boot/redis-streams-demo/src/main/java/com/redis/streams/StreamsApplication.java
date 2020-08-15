package com.redis.streams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class StreamsApplication {

	public static void main(String[] args) {

		SpringApplication.run(StreamsApplication.class, args);
	}



}
