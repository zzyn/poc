package com.spring.libs.flux.auth.core;

import com.spring.libs.flux.auth.core.entities.TokenPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.security.PublicKey;

@Component
@Slf4j
public class DecodeToken {
    private final PublicKey publicKey;
    private final ObjectMapper objectMapper;

    @Autowired
    public DecodeToken(PublicKey publicKey, ObjectMapper objectMapper) {
        this.publicKey = publicKey;
        this.objectMapper = objectMapper;
    }

    public <T extends TokenPayload> T execute(String jwtToken, Class<T> tokenClass) {
        if(StringUtils.isEmpty(jwtToken)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "token required");
        }

        try {
            Claims claims = Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(jwtToken)
                .getBody();
            T tokenPayload = objectMapper.convertValue(claims, tokenClass);
            return tokenPayload;
        } catch (ExpiredJwtException t) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "token expired");
        } catch (Exception t) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "invalid token");
        }
    }
}
