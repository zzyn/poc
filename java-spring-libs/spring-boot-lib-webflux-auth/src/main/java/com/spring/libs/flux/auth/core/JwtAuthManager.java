package com.spring.libs.flux.auth.core;

import com.spring.libs.flux.auth.core.entities.JwtAuthenticationToken;
import com.spring.libs.flux.auth.core.entities.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthManager implements ReactiveAuthenticationManager {
    private final AuthenticateToken authenticateToken;
    private final AuthorizeToken authorizeToken;
    private final SecurityProperties securityProperties;

    @Autowired
    public JwtAuthManager(AuthenticateToken authenticateToken,
                          @Autowired(required = false) AuthorizeToken authorizeToken,
                          SecurityProperties securityProperties) {
        this.authenticateToken = authenticateToken;
        this.authorizeToken = authorizeToken;
        this.securityProperties = securityProperties;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        if (authentication.isAuthenticated()) {
            return Mono.just(authentication);
        }

        JwtAuthenticationToken jwtAuthentication = (JwtAuthenticationToken) authentication;
        boolean disableAuthorization = securityProperties.isDisableAuthorization();

        return authenticateToken.execute(jwtAuthentication)
                .zipWith(Mono.just(disableAuthorization))
                .flatMap(tuple2 -> {
                    if (!tuple2.getT2()) {
                        return authorizeToken.execute((JwtAuthenticationToken) tuple2.getT1());
                    } else {
                        return Mono.just(tuple2.getT1());
                    }
                });
    }
}
