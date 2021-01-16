package com.spring.libs.flux.exception.autoconfigure.sync;

import com.spring.libs.flux.exception.exceptions.*;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;


public class GlobalExceptionHandlerSyncTest {

    private HttpServletResponse responseMock;
    private GlobalExceptionHandlerSync testee;

    @Before
    public void setUp() throws Exception {
        responseMock = mock(HttpServletResponse.class);
        testee = new GlobalExceptionHandlerSync();
    }

    @Test
    public void handleExceptions() throws IOException  {
        testee.handleExceptions(new Throwable("TEST"), responseMock);
        verify(responseMock).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void handleInvalidRequestException() throws IOException  {
        testee.handleInvalidRequestException(new InvalidRequestException("Test"), responseMock);
        verify(responseMock).sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    public void handleUnauthenticatedException() throws IOException  {
        testee.handleUnauthenticatedException(new UnauthenticatedException("TEST"), responseMock);
        verify(responseMock).sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Test
    public void handleUnauthorizedException() throws IOException  {
        testee.handleUnauthorizedException(new UnauthorizedException("Test"), responseMock);
        verify(responseMock).sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    public void handleResourceNotFoundException() throws IOException  {
        testee.handleResourceNotFoundException(new ResourceNotFoundException("Test"), responseMock);
        verify(responseMock).sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    public void handleBusinessConflictException() throws IOException  {
        testee.handleBusinessConflictException(new BusinessConflictException("Test"), responseMock);
        verify(responseMock).sendError(HttpServletResponse.SC_CONFLICT);
    }

    @Test
    public void handleUnsupportedOperationException() throws IOException  {
        testee.handleUnsupportedOperationException(new UnsupportedOperationException("Test"), responseMock);
        verify(responseMock).sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
    }
}