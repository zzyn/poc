package com.spring.libs.flux.auth.core;

import com.spring.libs.flux.auth.core.entities.AccessToken;
import com.spring.libs.flux.auth.core.entities.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class AuthenticateToken {
    private final DecodeToken decodeToken;

    @Autowired
    public AuthenticateToken(DecodeToken decodeToken) {
        this.decodeToken = decodeToken;
    }

    public Mono<Authentication> execute(JwtAuthenticationToken inputToken) {
        String accessRefToken = inputToken.getAccessToken();
        AccessToken accessPayload;
        try {
            accessPayload = decodeToken.execute(accessRefToken, AccessToken.class);
        } catch (Exception ex) {
            return Mono.error(ex);
        }

        JwtAuthenticationToken outputToken = new JwtAuthenticationToken(
                inputToken.getIdToken(),
                accessRefToken,
                Collections.emptyList());
        outputToken.setUserId(accessPayload.getOwner());
        return Mono.just(outputToken);
    }
}
