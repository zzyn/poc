package com.spring.libs.mvc.auth.config;


import com.spring.libs.mvc.auth.resolver.JwtContextMvcArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

public class WebMvcConfiguration implements WebMvcConfigurer {

    private final AuthConfig authConfig;
    private final JwtContextMvcArgumentResolver jwtContextMvcArgumentResolver;
    private final HandlerInterceptor[] interceptors;

    public WebMvcConfiguration(AuthConfig authConfig
            , JwtContextMvcArgumentResolver jwtContextMvcArgumentResolver
            , HandlerInterceptor... interceptors) {
        this.authConfig = authConfig;
        this.jwtContextMvcArgumentResolver = jwtContextMvcArgumentResolver;
        this.interceptors = interceptors;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(jwtContextMvcArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        Arrays.stream(interceptors).forEach(interceptor ->
                registry.addInterceptor(interceptor)
                        .addPathPatterns(authConfig.getPathPatterns())
                        .order(Integer.MIN_VALUE)
        );
    }
}
