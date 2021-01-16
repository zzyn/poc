package com.spring.libs.mvc.exception.handler;

import com.spring.libs.mvc.exception.ApiErrorCode;
import com.spring.libs.mvc.exception.ApiErrorResponse;
import com.spring.libs.mvc.exception.ApiException;
import com.spring.libs.mvc.exception.config.GlobalExceptionHandlerAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private GlobalExceptionHandlerAutoConfiguration.GlobalExceptionHandlerConfig config;

    @Autowired
    public GlobalExceptionHandler(GlobalExceptionHandlerAutoConfiguration.GlobalExceptionHandlerConfig config) {
        this.config = config;
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> defaultExceptionHandler(Exception ex, WebRequest request) {
        logger.error("{}, {}", ex.getClass().getName(), ex.getMessage());
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<Object> defaultExceptionHandler(ApiException ex, WebRequest request) {
        logger.error("{}, {}", ex.getClass().getName(), ex.getMessage());
        return handleExceptionInternal(ex, null, new HttpHeaders(), ex.getStatus(), request);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> defaultExceptionHandler(ConstraintViolationException ex, WebRequest request) {
        logger.error("{}, {}", ex.getClass().getName(), ex.getMessage());
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity(getApiErrorResponse(ex, status, ((ServletWebRequest) request).getRequest().getServletPath()), headers, status);
    }

    public ApiErrorResponse getApiErrorResponse(Exception ex, HttpStatus status, String path) {

        ApiErrorResponse response = new ApiErrorResponse()
                .setStatus(status.value())
                .setError(status.name())
                .setMessage(ex.getMessage())
                .setPath(path)
                .setTimestamp(System.currentTimeMillis())
                .setException(ex.toString());

        if (ex instanceof ApiException) {
            response.setCode(((ApiException) ex).getReason());
        } else {
            response.setCode(ApiErrorCode.UNDEFINED.getCode().toString());
        }

        if (config.isIncludeStacktrace()) {
            StringWriter stackTrace = new StringWriter();
            ex.printStackTrace(new PrintWriter(stackTrace));
            stackTrace.flush();
            response.setTrace(stackTrace.toString());
        }

        return response;
    }
}