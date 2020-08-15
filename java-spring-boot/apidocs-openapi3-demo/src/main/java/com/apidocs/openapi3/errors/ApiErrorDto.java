package com.apidocs.openapi3.errors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApiErrorDto implements Serializable {

    private Long timestamp;
    private String path;
    private Integer status;
    private String error;
    private String code;
    private String message;
    private String exception;
    private String trace;
}