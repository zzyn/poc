package ${groupId}.core.security;

import ${groupId}.core.config.KeyConfig;
import ${groupId}.core.entity.IdentityToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStream;
import java.security.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class IdentityTokenUtils {

    public static IdentityToken getIdentityToken(String token, ResourceLoader resourceLoader, KeyConfig keyConfig) throws  Exception{

        InputStream inputStream = resourceLoader.getClassLoader().getResourceAsStream(keyConfig.getPublicKey());
        PublicKey publicKey = KeyUtils.getPublicKey(inputStream);
        IdentityToken identityToken = decodeToken(token, publicKey);
        return identityToken;
    }

    public static String encodeToken(IdentityToken identityToken, PrivateKey privateKey) throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(identityToken);

        JwtBuilder jwtBuilder =  Jwts
                .builder()
                .setHeaderParam("typ", "JWT")
                .setPayload(data)
                .signWith(privateKey, SignatureAlgorithm.RS256);

        return  jwtBuilder.compact();
    }

    public static IdentityToken decodeToken(String jwtData, PublicKey publicKey) throws Exception{

        Claims claims = (Claims)Jwts
                .parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(jwtData)
                .getBody();

        ObjectMapper mapper = new ObjectMapper();

        String data = mapper.writeValueAsString(claims);

        IdentityToken identityToken = mapper.readValue(data, IdentityToken.class);

        return identityToken;
    }

    public static boolean verifyToken(String token, PublicKey publicKey){

        boolean isValid = false;

        Claims claims = Jwts
                .parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();

        if(Objects.nonNull(claims) && claims.size() > 0){
            if(Objects.nonNull(claims.getExpiration()) && claims.getExpiration().toInstant().isAfter(LocalDateTime.now().toInstant(ZoneOffset.UTC))){
                isValid = true;
            }
        }
        return isValid;
    }
}
