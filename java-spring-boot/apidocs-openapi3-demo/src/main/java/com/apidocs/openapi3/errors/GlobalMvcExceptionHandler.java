package com.apidocs.openapi3.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class GlobalMvcExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> defaultExceptionHandler(HttpServletRequest request, WebRequest webRequest, HttpServletResponse response, Exception e) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String defaultApiErrorCode = ApiErrorCode.APP.getCode().toString();
        ApiException ae;
        if (e instanceof ApiException) {
            ae = (ApiException) e;
            status = ae.getStatus();
        } else {
            ae = new ApiException(status, defaultApiErrorCode, e);
        }

        ApiErrorDto errorModel = ApiErrorUtils.getApiError(request, ae, true);

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return handleExceptionInternal(ae, errorModel, headers, status, webRequest);
    }
}