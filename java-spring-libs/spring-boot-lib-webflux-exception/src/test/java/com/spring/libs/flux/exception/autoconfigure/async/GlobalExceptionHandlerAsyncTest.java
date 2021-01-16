package com.spring.libs.flux.exception.autoconfigure.async;

import com.spring.libs.flux.exception.autoconfigure.webflux.GlobalExceptionHandlerAsync;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

public class GlobalExceptionHandlerAsyncTest {

    private GlobalExceptionHandlerAsync testee = new GlobalExceptionHandlerAsync();

    @Test
    public void handleExceptions_ServerWebInputException() {
        try {
            Mono<ResponseEntity> resultMono =  testee.handleExceptions(new ServerWebInputException("SOME_PROBLEM"));
            resultMono.block();
        } catch (ResponseStatusException e) {
            assertThat(e.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }
}
