package com.webflux.cassandra.demo.core.bind;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsistencyContextConfig {

    @Bean
    public ConsistencyContextArgumentResolver getConsistencyContextArgumentResolver(){
        return new ConsistencyContextArgumentResolver();
    }
}
