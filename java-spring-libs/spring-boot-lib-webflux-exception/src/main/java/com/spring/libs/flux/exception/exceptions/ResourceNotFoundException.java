package com.spring.libs.flux.exception.exceptions;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(int errorCode, String message) {
        super(errorCode, message);
    }

    public ResourceNotFoundException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
