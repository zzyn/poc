package com.spring.libs.config;

import com.spring.libs.bind.HttpContextMvcArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

public class WebMvcBindConfiguration implements WebMvcConfigurer {
    private final HttpContextMvcArgumentResolver httpContextMvcArgumentResolver;

    public WebMvcBindConfiguration(HttpContextMvcArgumentResolver httpContextMvcArgumentResolver){
        this.httpContextMvcArgumentResolver = httpContextMvcArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(httpContextMvcArgumentResolver);
    }
}
