package com.mq.kafka.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

@Slf4j
@EnableKafka
@Configuration
public class DefaultKafkaFactoryConfig {

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> batchFactory(ConsumerFactory<String, String> consumerFactory, KafkaProperties kafkaProperties) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setBatchListener(kafkaProperties.getListener().getType().equals(KafkaProperties.Listener.Type.BATCH));
        factory.setConcurrency(kafkaProperties.getListener().getConcurrency());
        factory.setErrorHandler(new ListenerErrorHandler());
        ContainerProperties containerProperties = factory.getContainerProperties();
        containerProperties.setPollTimeout(kafkaProperties.getListener().getPollTimeout().toMillis());
        containerProperties.setAckMode(kafkaProperties.getListener().getAckMode());
        return factory;
    }
}
