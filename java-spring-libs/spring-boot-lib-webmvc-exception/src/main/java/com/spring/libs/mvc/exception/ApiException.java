package com.spring.libs.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ApiException extends ResponseStatusException {

    public ApiException(HttpStatus status, ApiErrorCode code, String message) {
        this(status, code, new RuntimeException(message));
    }

    public ApiException(HttpStatus status, ApiErrorCode code, Throwable cause) {
        super(status, code.getCode().toString(), cause);
    }

    public ApiException(HttpStatus status, Throwable cause) {
        this(status, ApiErrorCode.CLIENT, cause);
    }

    public ApiException(Throwable cause) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, ApiErrorCode.CLIENT, cause);
    }

    @Override
    public String getMessage() {
        return this.getCause().getMessage();
    }
}
