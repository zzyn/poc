package com.spring.libs.redis.config;

import com.spring.libs.redis.service.RedisService;
import com.spring.libs.redis.service.impl.RedisServiceDefaultImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.stream.Collectors;

import static com.spring.libs.redis.constants.RedisConstants.*;

public class RedisAutoConfiguration {

    @Value("${spring.redis.app-enabled}")
    private Boolean enabled;

    @Value("${spring.redis.app-ttl}")
    private Duration ttl;

    @Value("${spring.redis.app-prefix}")
    private String prefix;

    @Value("${spring.profiles.active}")
    private String profile;

    @Bean
    @ConditionalOnMissingBean
    public RedisConfig getRedisConfig() {
        return new RedisConfig(enabled, prefix, ttl, profile);
    }

    @Bean(name = FACTORY_NAME_REDIS)
    @ConditionalOnMissingBean
    public RedisConnectionFactory getRedisConnectionFactory(RedisProperties redisProperties) {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setPassword(redisProperties.getPassword());
        redisClusterConfiguration.setClusterNodes(redisProperties.getCluster().getNodes().stream()
                .map(x -> {
                    String[] node = x.split(COLON);
                    return new RedisNode(node[0], Integer.valueOf(node[1]));
                }).collect(Collectors.toList()));

        return new LettuceConnectionFactory(redisClusterConfiguration);
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisTemplate<String, Object> getRedisTemplate(@Qualifier(value = FACTORY_NAME_REDIS) RedisConnectionFactory factory) {
        final GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        final StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        return new RedisTemplate<String, Object>() {{
            setConnectionFactory(factory);
            setDefaultSerializer(genericJackson2JsonRedisSerializer);
            setEnableDefaultSerializer(true);
            setKeySerializer(stringRedisSerializer);
            setHashKeySerializer(stringRedisSerializer);
            setValueSerializer(genericJackson2JsonRedisSerializer);
            setHashValueSerializer(genericJackson2JsonRedisSerializer);
            afterPropertiesSet();
        }};
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisService getRedisService(RedisTemplate<String, Object> redisTemplate, RedisConfig config) {
        return new RedisServiceDefaultImpl(redisTemplate, config);
    }

    @Bean
    @ConditionalOnMissingBean
    public CacheManager getCacheManager(@Qualifier(value = FACTORY_NAME_REDIS) RedisConnectionFactory factory, RedisConfig config) {
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(config.getTtl())
                        .disableCachingNullValues()
                        .prefixKeysWith(config.getPrefix().concat(UNDERLINE))
                        .serializeValuesWith(RedisSerializationContext.SerializationPair
                                .fromSerializer(new GenericJackson2JsonRedisSerializer())))
                .build();
    }
}
