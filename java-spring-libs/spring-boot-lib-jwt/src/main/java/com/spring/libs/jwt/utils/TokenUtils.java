package com.spring.libs.jwt.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.libs.jwt.constants.JwtConstants;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.FixedClock;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TokenUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T extends Serializable> String encodeToken(T token, PrivateKey privateKey) throws Exception {
        return Jwts.builder().setHeaderParam(JwtConstants.TYP_HEADER, JwtConstants.JWT_HEADER_VALUE).setPayload(mapper.writeValueAsString(token))
                .signWith(privateKey, SignatureAlgorithm.RS256).compact();
    }

    public static <T extends Serializable> T decodeToken(String jwtData, PublicKey publicKey, Class<T> clazz) throws Exception {
        return decodeToken(jwtData, publicKey, new FixedClock(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC))), clazz);
    }

    public static <T extends Serializable> T decodeToken(String jwtData, PublicKey publicKey, Clock clock, Class<T> clazz) throws Exception {
        return mapper.readValue(
                mapper.writeValueAsString(Jwts.parser()
                        .setClock(clock)
                        .setSigningKey(publicKey)
                        .parseClaimsJws(jwtData)
                        .getBody())
                , clazz);
    }

    public static void verifyToken(String token, PublicKey publicKey) {
        verifyToken(token, publicKey, new FixedClock(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC))));
    }

    public static void verifyToken(String token, PublicKey publicKey, Clock clock) {
        Jwts.parser().setClock(clock).setSigningKey(publicKey).parseClaimsJws(token).getBody();
    }
}
