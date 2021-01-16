package com.spring.libs.config;

import com.spring.libs.bind.HttpContextMvcArgumentResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MvcBindAutoConfig {

    @Bean
    public HttpContextMvcArgumentResolver getHttpContextResolver() {
        return new HttpContextMvcArgumentResolver();
    }

    @Bean
    @ConditionalOnMissingBean
    public WebMvcBindConfiguration getWebMvcBindConfiguration(HttpContextMvcArgumentResolver httpContextMvcArgumentResolver){
        return new WebMvcBindConfiguration(httpContextMvcArgumentResolver);
    }
}
