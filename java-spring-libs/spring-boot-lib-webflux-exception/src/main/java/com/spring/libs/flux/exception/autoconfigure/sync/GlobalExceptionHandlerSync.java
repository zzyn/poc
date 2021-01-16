package com.spring.libs.flux.exception.autoconfigure.sync;

import com.spring.libs.flux.exception.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerSync extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public void handleExceptions(Throwable exc,
                                 HttpServletResponse response) throws IOException {
        logException(exc);
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public void handleInvalidRequestException(InvalidRequestException exc,
                                              HttpServletResponse response) throws IOException {
        logException(exc);
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public void handleUnauthenticatedException(UnauthenticatedException exc,
                                               HttpServletResponse response) throws IOException {
        logException(exc);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public void handleUnauthorizedException(UnauthorizedException exc,
                                            HttpServletResponse response) throws IOException {
        logException(exc);
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public void handleResourceNotFoundException(ResourceNotFoundException exc,
                                                HttpServletResponse response) throws IOException {
        logException(exc);
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @ExceptionHandler(BusinessConflictException.class)
    public void handleBusinessConflictException(BusinessConflictException exc,
                                                HttpServletResponse response) throws IOException {
        logException(exc);
        response.sendError(HttpServletResponse.SC_CONFLICT);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public void handleUnsupportedOperationException(UnsupportedOperationException exc,
                                                    HttpServletResponse response) throws IOException {
        logException(exc);
        response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
    }

    private void logException(Throwable t) {
        log.error("Exception caught", t);
    }
}
