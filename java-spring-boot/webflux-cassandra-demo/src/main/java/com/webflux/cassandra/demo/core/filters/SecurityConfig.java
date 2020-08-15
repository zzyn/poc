package com.webflux.cassandra.demo.core.filters;

import com.webflux.cassandra.demo.core.config.KeyConfig;
import com.webflux.cassandra.demo.core.exception.ApiException;
import com.webflux.cassandra.demo.core.jwt.AccessTokenUtils;
import com.webflux.cassandra.demo.core.jwt.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.security.PublicKey;
import java.util.Objects;

import static com.webflux.cassandra.demo.core.CoreConstants.ACCESS_TOKEN_HEADER;
import static com.webflux.cassandra.demo.core.CoreConstants.DEFAULT_API_ROUTE;


@Configuration
public class SecurityConfig {

    private final KeyConfig keyConfig;
    private final ResourceLoader resourceLoader;

    @Autowired
    public SecurityConfig(KeyConfig keyConfig, ResourceLoader resourceLoader) {
        this.keyConfig = keyConfig;
        this.resourceLoader = resourceLoader;
    }

    @Bean
    @Order(-1)
    public WebFilter appSecurityFilter() {

        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            HttpHeaders headers = request.getHeaders();
            String path = request.getPath().value();

            if (path.startsWith(DEFAULT_API_ROUTE)) {

                if (!headers.containsKey(ACCESS_TOKEN_HEADER)) {
                    return Mono.error(new ApiException(HttpStatus.BAD_REQUEST, String.format("header : %s is requierd", ACCESS_TOKEN_HEADER)));
                }

                String jwt = headers.getFirst(ACCESS_TOKEN_HEADER);

                if (Objects.isNull(jwt) || jwt.isEmpty()) {
                    return Mono.error(new ApiException(HttpStatus.BAD_REQUEST, String.format("header : %s is empty", ACCESS_TOKEN_HEADER)));
                }

                PublicKey publicKey = null;
                try {
                    InputStream inputStream = resourceLoader.getClassLoader().getResourceAsStream(keyConfig.getPublicKey());
                    publicKey = KeyUtils.getPublicKey(inputStream);
                } catch (Exception e) {
                    return Mono.error(new ApiException(HttpStatus.UNAUTHORIZED, "invalid ssl key"));
                }

                Boolean isValidToken;
                try {
                    isValidToken = AccessTokenUtils.verifyToken(jwt, publicKey);
                } catch (Exception e) {
                    return Mono.error(new ApiException(HttpStatus.UNAUTHORIZED, e.getMessage()));
                }

                if (!isValidToken) {
                    return Mono.error(new ApiException(HttpStatus.UNAUTHORIZED, "invalid token"));
                }
            }
            return chain.filter(ctx);
        };
    }
}
