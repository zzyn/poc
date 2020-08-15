package com.mq.kafka.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.ErrorHandler;

@Slf4j
public class ListenerErrorHandler implements ErrorHandler {

    @Override
    public void handle(Exception e, ConsumerRecord<?, ?> data) {

        String msg = String.format("topic: %s, partition: %s, offset: %s, timestamp: %s, error: %s",  data.topic(), data.partition(), data.offset(), data.timestamp(), e.getMessage());
        log.error(msg, e);
    }

    @Override
    public void handle(Exception e, ConsumerRecord<?, ?> data, Consumer<?, ?> consumer) {

        String msg = String.format("topic: %s, partition: %s, offset: %s, timestamp: %s, error: %s", data.topic(), data.partition(), data.offset(), data.timestamp(),  e.getMessage());
        log.error(msg, e);
    }
}
