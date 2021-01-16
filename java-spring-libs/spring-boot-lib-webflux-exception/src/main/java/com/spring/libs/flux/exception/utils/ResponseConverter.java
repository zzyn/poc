package com.spring.libs.flux.exception.utils;

import com.spring.libs.flux.exception.dto.ApiErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

public class ResponseConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ResponseConverter() {
    }

    public static <T> Mono<T> toResult(ClientResponse response, Class<T> resultClass) {
        return Mono.just(response)
                .flatMap(resp -> {
                    if (resp.statusCode().isError()) {
                        return toException(resp);
                    }
                    return resp.bodyToMono(resultClass);
                });
    }

    public static <T> Mono<T> toResult(ClientResponse response, ParameterizedTypeReference<T> typeReference) {
        return Mono.just(response)
                .flatMap(resp -> {
                    if (resp.statusCode().isError()) {
                        return toException(resp);
                    }
                    return resp.bodyToMono(typeReference);
                });
    }

    public static <T> Mono<T> toException(ClientResponse clientResponse) {
        if (HttpErrorChecker.isCustomError(clientResponse.statusCode())) {
            return clientResponse
                    .bodyToMono(String.class)
                    .defaultIfEmpty("")
                    .map(errorBody -> {
                        ApiErrorResponse errorResponse;
                        try {
                            errorResponse = objectMapper.readValue(errorBody, ApiErrorResponse.class);
                            if (StringUtils.isEmpty(errorResponse.getMessage())) {
                                errorResponse.setMessage(errorBody);
                            }
                        } catch (Exception e) {
                            errorResponse = new ApiErrorResponse();
                            errorResponse.setMessage(errorBody);
                        }
                        return errorResponse;
                    }).map(apiErrorResponse -> {
                        apiErrorResponse.setStatus(clientResponse.rawStatusCode());
                        return apiErrorResponse;
                    }).map(ApiErrorResponse::toApiException).flatMap(Mono::error);
        } else {
            return clientResponse.bodyToMono(String.class)
                    .defaultIfEmpty("")
                    .map(errorBody -> new HttpServerErrorException(clientResponse.statusCode(), errorBody))
                    .flatMap(Mono::error);
        }
    }
}
