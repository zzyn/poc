package com.spring.libs.flux.auth.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public Mono<ResponseEntity> handleExceptions(AccessDeniedException e) {
        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e));
    }
}
