package com.spring.libs.flux.exception.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class InvalidRequestException extends ApiException {

    private final List<?> errors;

    public InvalidRequestException(String message) {
        super(message);
        this.errors = new ArrayList<>();
    }

    public InvalidRequestException(String message, List<?> errors) {
        super(message);
        this.errors = errors;
    }

    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
        this.errors = new ArrayList<>();
    }

    public InvalidRequestException(int errorCode, String message) {
        super(errorCode, message);
        this.errors = new ArrayList<>();
    }

    public InvalidRequestException(int errorCode, String message, List<?> errors) {
        super(errorCode, message);
        this.errors = errors;
    }

    public InvalidRequestException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
        this.errors = new ArrayList<>();
    }
}
