package com.spring.libs.flux.exception.exceptions;

public class UnauthenticatedException extends ApiException {

    public UnauthenticatedException(String message) {
        super(message);
    }

    public UnauthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthenticatedException(int errorCode, String message) {
        super(errorCode, message);
    }

    public UnauthenticatedException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
