package com.webflux.cassandra.demo.core.filters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import java.net.InetSocketAddress;
import java.util.Objects;

import static com.webflux.cassandra.demo.core.CoreConstants.CLIENT_IP_HEADER;


@Configuration
public class IpAddressConfig {

    @Bean
    @Order(-99)
    public WebFilter getRemoteIpAddress() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            InetSocketAddress remoteAddress = ctx.getRequest().getRemoteAddress();
            String clientIp = Objects.requireNonNull(remoteAddress).getAddress().getHostAddress();
            ServerHttpRequest mutatedServerHttpRequest = ctx.getRequest().mutate().header(CLIENT_IP_HEADER, clientIp).build();
            ServerWebExchange mutatedServerWebExchange = ctx.mutate().request(mutatedServerHttpRequest).build();
            return chain.filter(mutatedServerWebExchange);
        };
    }
}
