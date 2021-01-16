package com.spring.libs.flux.auth.core.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Getter
@ToString
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private String accessToken;
    private String idToken;
    @Setter
    private String userId;

    public JwtAuthenticationToken(String idToken, String accessToken) {
        this(idToken, accessToken, Collections.emptyList());
    }

    public JwtAuthenticationToken(String idToken, String accessToken, Collection<GrantedAuthority> authorities) {
        super(authorities);
        this.idToken = idToken;
        this.accessToken = accessToken;
    }

    @Override
    public Object getCredentials() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Object getPrincipal() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
