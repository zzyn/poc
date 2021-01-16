package com.spring.libs.flux.auth.core;

import com.spring.libs.flux.auth.core.entities.JwtAuthenticationToken;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtTokenExtractor implements ServerAuthenticationConverter {

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String accessToken = request.getHeaders().getFirst(Constants.HttpConstants.HEADER_ACCESS_TOKEN);

        if(StringUtils.isEmpty(accessToken)){
            HttpCookie cookie = request.getCookies().getFirst(Constants.HttpConstants.HEADER_ACCESS_TOKEN);
            if(cookie != null) {
                accessToken = cookie.getValue();
            }
        }

        String idToken = request.getHeaders().getFirst(Constants.HttpConstants.HEADER_X_EF_TOKEN);
        if(StringUtils.isEmpty(idToken)){
            HttpCookie cookie =request.getCookies().getFirst(Constants.HttpConstants.HEADER_X_EF_TOKEN);
            if(cookie!=null){
                idToken = cookie.getValue();
            }
        }

        return Mono.just(new JwtAuthenticationToken(idToken, accessToken));
    }
}
