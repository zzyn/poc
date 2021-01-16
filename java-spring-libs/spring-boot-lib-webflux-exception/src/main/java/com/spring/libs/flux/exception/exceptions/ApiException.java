package com.spring.libs.flux.exception.exceptions;

/**
 * Api level exception. Use the errorCode to specify internal application error codes.
 */
public class ApiException extends RuntimeException {

    private final int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public ApiException(String message) {
        this(0, message);
    }

    public ApiException(String message, Throwable cause) {
        this(0, message, cause);
    }

    public ApiException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApiException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
