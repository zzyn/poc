package com.spring.libs.mvc.exception;

import java.io.Serializable;

public class ApiErrorResponse implements Serializable {

    private String path;

    private Integer status;

    private String error;

    private String code;

    private String message;

    private String exception;

    private String trace;

    private Long timestamp;

    public Long getTimestamp() {
        return timestamp;
    }

    public ApiErrorResponse setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ApiErrorResponse setPath(String path) {
        this.path = path;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ApiErrorResponse setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getError() {
        return error;
    }

    public ApiErrorResponse setError(String error) {
        this.error = error;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ApiErrorResponse setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getException() {
        return exception;
    }

    public ApiErrorResponse setException(String exception) {
        this.exception = exception;
        return this;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }
}
