package com.redis.locks;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class RedisConfig {


    @Bean
    public Config config(){
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress("redis://cnedtechstgrediscluster-1.nsmena.clustercfg.cnn1.cache.amazonaws.com.cn:6379");

        ;


        return config;
    }

    @Bean
    public RedissonClient redissonClient(Config config){
        return Redisson.create(config);
    }
}
