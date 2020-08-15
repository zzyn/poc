package com.redis.streams;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.stream.Collectors;


@Slf4j
@Configuration
public class RedisLettuceConfig {


    @Bean
    public RedisConnectionFactory getRedisConnectionFactory(RedisProperties redisProperties) {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setPassword(redisProperties.getPassword());
        redisClusterConfiguration.setClusterNodes(redisProperties.getCluster().getNodes().stream()
                .map(x -> {
                    String[] node = x.split(":");
                    return new RedisNode(node[0], Integer.valueOf(node[1]));
                }).collect(Collectors.toList()));

        return new LettuceConnectionFactory(redisClusterConfiguration);
    }
}
