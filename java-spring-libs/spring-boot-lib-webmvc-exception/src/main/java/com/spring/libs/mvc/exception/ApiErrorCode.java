package com.spring.libs.mvc.exception;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ApiErrorCode {

    APP(100000),
    DB(200000),
    CACHE(300000),
    CLIENT(400000),
    UNDEFINED(Integer.MAX_VALUE);

    private Integer code;

    @JsonCreator
    ApiErrorCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
