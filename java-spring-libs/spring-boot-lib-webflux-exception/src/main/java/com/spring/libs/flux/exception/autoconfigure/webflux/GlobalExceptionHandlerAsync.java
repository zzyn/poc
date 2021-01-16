package com.spring.libs.flux.exception.autoconfigure.webflux;

import com.spring.libs.flux.exception.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAsync {


    @ExceptionHandler
    public Mono<ResponseEntity> handleExceptions(Throwable t) {
        return Mono.error(t);
    }

    @ExceptionHandler
    public Mono<ResponseEntity> handleExceptions(InvalidRequestException e) {
        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e));
    }

    @ExceptionHandler
    public Mono<ResponseEntity> handleExceptions(UnauthenticatedException e) {
        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e));
    }

    @ExceptionHandler
    public Mono<ResponseEntity> handleExceptions(UnauthorizedException e) {
        return Mono.error(new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e));
    }

    @ExceptionHandler
    public Mono<ResponseEntity> handleExceptions(ResourceNotFoundException e) {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e));
    }

    @ExceptionHandler
    public Mono<ResponseEntity> handleExceptions(BusinessConflictException e) {
        return Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e));
    }

    @ExceptionHandler
    public Mono<ResponseEntity> handleExceptions(UnsupportedOperationException e) {
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, e.getMessage(), e));
    }

    // Handles exception throw by spring itself when bind rest controller parameters.
    @ExceptionHandler
    public Mono<ResponseEntity> handleExceptions(ServerWebInputException e) {
        log.error("Server Binding Exception", e);
        // Get exception cause when available, otherwise the message for the exception itself is ambiguous.
        Throwable t = e.getCause() != null ? e.getCause() : e;
        return Mono.error(new ResponseStatusException(e.getStatus(), t.getMessage(), t));
    }

    @ExceptionHandler
    public Mono<ResponseEntity> handleExceptions(ConstraintViolationException e) {
        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e));
    }
}
