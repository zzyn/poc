package com.redis.pubsub;

import com.redis.pubsub.message.Receiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String[] args) throws InterruptedException {

		//SpringApplication.run(Application.class, args);

		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
		Receiver receiver = ctx.getBean(Receiver.class);

		while (receiver.getCount() == 0) {
			log.info("Sending message...");
			template.convertAndSend("chat", "Hello from Redis!");
			Thread.sleep(500L);
		}

		//System.exit(0);
	}



}
