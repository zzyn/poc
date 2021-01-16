package com.spring.libs.mvc.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class CorsAutoConfiguration {
    @Value("${cors.allowed-headers:Content-Type, Accept, Authorization, If-Match, If-Modified-Since, If-None-Match, If-Unmodified-Since, Accept-Encoding, Accept-Language, Origin, X-EF-TOKEN, X-EF-ID, X-EF-ACCESS}")
    private String allowedHeaders;

    @Value("${cors.allowed-methods:GET, PUT, POST, DELETE, PATCH, OPTIONS}")
    private String allowedMethods;

    @Value("${cors.allowed-origin:*}")
    private String allowedOrigin;

    @Value("${cors.max-age:3600}")
    private String maxAge;

    @Value("${cors.default-api-route:/api/}")
    private String defaultApiRoute;

    @Bean
    public CorsConfig corsConfig() {
        String[] defaultApiRouteArray = defaultApiRoute.split(",");
        Arrays.parallelSetAll(defaultApiRouteArray,i->defaultApiRouteArray[i].trim());
        CorsConfig corsConfig = new CorsConfig(allowedHeaders, allowedMethods, allowedOrigin, maxAge, defaultApiRouteArray);
        return corsConfig;
    }

    @Bean
    public FilterRegistrationBean corsMvcFilterRegistration(CorsConfig corsConfig) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CorsMvcFilter corsMvcFilter = new CorsMvcFilter(corsConfig);
        registrationBean.setFilter(corsMvcFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("CorsMvcFilter");
        registrationBean.setOrder(-2);
        return registrationBean;
    }
}
