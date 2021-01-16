package com.spring.libs.flux.auth.config;

import com.spring.libs.flux.auth.core.entities.SecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class AuthClientConfig {
    private final SecurityProperties securityProperties;

    public AuthClientConfig(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "kt.security", name = "disable-authorization", havingValue = "false", matchIfMissing = true)
    public WebClient authorizationClient() {
        return WebClient.builder()
                .baseUrl(securityProperties.getAuthorizationUri())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
