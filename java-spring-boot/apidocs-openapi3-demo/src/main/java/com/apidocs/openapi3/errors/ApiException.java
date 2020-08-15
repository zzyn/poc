package com.apidocs.openapi3.errors;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public class ApiException extends ResponseStatusException {

    private String msg;

    public ApiException(HttpStatus status) {
        super(status);
    }

    public ApiException(HttpStatus status, @Nullable String code) {
        super(status, code);
    }

    public ApiException(HttpStatus status, @Nullable String code, @Nullable Throwable cause) {
        super(status, code, cause);
    }

    public ApiException(HttpStatus status, @Nullable String code, @Nullable String msg, @Nullable Throwable cause){
        super(status, code, cause);
        this.msg = msg;
    }

    @Override
    public String getMessage(){
        return Objects.isNull(this.msg)? super.getMessage() : this.msg;
    }
}
