package com.webflux.cassandra.demo.core.cache;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class ReactiveRedisConfig {

    @Bean
    @Qualifier(value = "reactive_redis")
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(RedisProperties redisProperties){

        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();

        redisProperties.getCluster().getNodes().stream().forEach(x-> {
            String[] node = x.split(":");
            redisClusterConfiguration.addClusterNode(new RedisNode(node[0], Integer.valueOf(node[1])));
        });
        redisClusterConfiguration.setPassword(redisProperties.getPassword());

        return new LettuceConnectionFactory(redisClusterConfiguration);
    }

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(@Qualifier(value = "reactive_redis") ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {

        RedisSerializationContext.RedisSerializationContextBuilder<String, Object> builder = RedisSerializationContext.newSerializationContext();

        builder.key(RedisSerializer.string())
                .value(RedisSerializer.json())
                .hashKey(RedisSerializer.string())
                .hashValue(RedisSerializer.json());

        RedisSerializationContext<String, Object> serializationContext = builder.build();

        ReactiveRedisTemplate<String, Object> template = new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, serializationContext);

        return template;
    }
}