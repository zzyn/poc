package com.webflux.cassandra.demo.api.admin;

import com.webflux.cassandra.demo.core.config.KeyConfig;
import com.webflux.cassandra.demo.core.entity.AccessToken;
import com.webflux.cassandra.demo.core.jwt.AccessTokenUtils;
import com.webflux.cassandra.demo.core.jwt.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

import java.io.InputStream;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApiIgnore
@RestController
@RequestMapping("/admin/token")
public class TokenController {

    private final ResourceLoader resourceLoader;
    private final KeyConfig keyConfig;

    @Autowired
    public TokenController(ResourceLoader resourceLoader, KeyConfig keyConfig) {
        this.resourceLoader = resourceLoader;
        this.keyConfig = keyConfig;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/generate-service-token",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Mono<String> generateServiceToken() throws Exception {

       Long issuedAt =  LocalDateTime
               .now()
               .toInstant(ZoneOffset.UTC)
               .getEpochSecond();

       Long expiration = LocalDateTime
                .now()
                .plusYears(100)
                .toInstant(ZoneOffset.UTC)
                .getEpochSecond();

        AccessToken token = new AccessToken()
                .setSub("access-token")
                .setAppId(UUID.randomUUID().toString())
                .setAppName("platform-plan-svc")
                .setType("service")
                .setIssuedAt(issuedAt)
                .setExpirationTime(expiration);

        InputStream inputStream = resourceLoader.getClassLoader().getResourceAsStream(keyConfig.getPrivateKey());
        PrivateKey privateKey = KeyUtils.getPrivateKey(inputStream);

        return Mono.justOrEmpty(AccessTokenUtils.encodeToken(token, privateKey));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/generate-personal-token",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Mono<String> generatePersonalToken() throws Exception {

        Long issuedAt =  LocalDateTime
                .now()
                .toInstant(ZoneOffset.UTC)
                .getEpochSecond();

        Long expiration = LocalDateTime
                .now()
                .plusMonths(1)
                .toInstant(ZoneOffset.UTC)
                .getEpochSecond();

        List<String> roles = new ArrayList<>();
        roles.add("admin");
        roles.add("reader");
        roles.add("writer");

        AccessToken token = new AccessToken()
                .setSub("access-token")
                .setAppId(UUID.randomUUID().toString())
                .setAppName("platform-plan-svc")
                .setType("personal")
                .setRoles(roles)
                .setIssuedAt(issuedAt)
                .setExpirationTime(expiration);

        InputStream inputStream = resourceLoader.getClassLoader().getResourceAsStream(keyConfig.getPrivateKey());
        PrivateKey privateKey = KeyUtils.getPrivateKey(inputStream);

        return Mono.justOrEmpty(AccessTokenUtils.encodeToken(token, privateKey));
    }

}
