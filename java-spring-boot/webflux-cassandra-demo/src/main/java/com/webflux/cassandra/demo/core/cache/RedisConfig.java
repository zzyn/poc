package com.webflux.cassandra.demo.core.cache;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    @Qualifier(value = "redis")
    public RedisConnectionFactory redisConnectionFactory(RedisProperties redisProperties){

        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();

        redisProperties.getCluster().getNodes().stream().forEach(x-> {
            String[] node = x.split(":");
            redisClusterConfiguration.addClusterNode(new RedisNode(node[0], Integer.valueOf(node[1])));
        });
        redisClusterConfiguration.setPassword(redisProperties.getPassword());

        return new LettuceConnectionFactory(redisClusterConfiguration);
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisTemplate<String, Object> redisTemplate(@Qualifier(value = "redis") RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();

        template.setConnectionFactory(factory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        template.setDefaultSerializer(genericJackson2JsonRedisSerializer);
        template.setEnableDefaultSerializer(true);
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(genericJackson2JsonRedisSerializer);
        template.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }
}
