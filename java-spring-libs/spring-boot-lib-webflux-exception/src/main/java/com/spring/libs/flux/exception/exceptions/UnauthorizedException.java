package com.spring.libs.flux.exception.exceptions;

public class UnauthorizedException extends ApiException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(int errorCode, String message) {
        super(errorCode, message);
    }

    public UnauthorizedException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
