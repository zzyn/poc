package com.spring.libs.flux.exception.exceptions;

public class BusinessConflictException extends ApiException {

    public BusinessConflictException(String message) {
        super(message);
    }

    public BusinessConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessConflictException(int errorCode, String message) {
        super(errorCode, message);
    }

    public BusinessConflictException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
