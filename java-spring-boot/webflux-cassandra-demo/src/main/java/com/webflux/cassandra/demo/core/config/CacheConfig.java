package com.webflux.cassandra.demo.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CacheConfig {

    @Value("${spring.redis.app-ttl}")
    private Long redisGlobalTtl;

    @Value("${spring.redis.app-enabled}")
    private Boolean redisGlobalEnabled;

    public Duration globalCacheTtl(){
        return Duration.ofSeconds(redisGlobalTtl);
    }

    public Boolean  globalEnabledCache() { return redisGlobalEnabled; }
}