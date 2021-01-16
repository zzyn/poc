package com.spring.libs.mvc.exception.config;

import com.spring.libs.mvc.exception.handler.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "exception.handler", name = "enabled", havingValue = "true")
public class GlobalExceptionHandlerAutoConfiguration {

    @Value("${exception.handler.include-stacktrace}")
    private boolean includeStacktrace;

    @Bean
    public GlobalExceptionHandlerConfig globalExceptionHandlerConfig() {
        return new GlobalExceptionHandlerConfig(includeStacktrace);
    }

    @Bean
    GlobalExceptionHandler exceptionHandler(GlobalExceptionHandlerConfig config) {
        return new GlobalExceptionHandler(config);
    }

    public class GlobalExceptionHandlerConfig {
        private boolean includeStacktrace;

        public GlobalExceptionHandlerConfig(boolean includeStacktrace) {
            this.includeStacktrace = includeStacktrace;
        }

        public boolean isIncludeStacktrace() {
            return includeStacktrace;
        }
    }
}
