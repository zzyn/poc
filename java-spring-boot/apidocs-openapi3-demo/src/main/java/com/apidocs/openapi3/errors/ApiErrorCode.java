package com.apidocs.openapi3.errors;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
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
}