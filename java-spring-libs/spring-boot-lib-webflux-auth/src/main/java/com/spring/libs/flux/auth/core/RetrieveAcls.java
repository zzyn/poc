package com.spring.libs.flux.auth.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static com.spring.libs.flux.auth.core.Constants.HttpConstants.HEADER_ACCESS_TOKEN;

@Component
public class RetrieveAcls {
    private final WebClient authorizationClient;

    @Autowired
    public RetrieveAcls(@Autowired(required = false) WebClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public Mono<List<Map<String, Object>>> execute(String accessRefToken) {
        WebClient webClient = authorizationClient.mutate().defaultHeader(HEADER_ACCESS_TOKEN, accessRefToken)
                .build();
        String aclApi = "/internal/api/v2/auth/acl";
        return webClient.get()
                .uri(aclApi)
                .exchange()
                .flatMap(clientResponse -> {
                            HttpStatus status = clientResponse.statusCode();
                            if (status.is2xxSuccessful()) {
                                return clientResponse.bodyToMono(new ParameterizedTypeReference<>() {
                                });
                            } else {
                                return clientResponse.bodyToMono(String.class)
                                        .map(msg -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, msg))
                                        .flatMap(Mono::error);
                            }
                        }
                );
    }
}
