package com.spring.libs.flux.exception.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.libs.flux.exception.exceptions.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiExceptionFactoryMethodTest {

    @Test
    public void given400Response_whenConvert_thenGetInvalidRequestException() throws IOException {
        var exception = getExceptionFromResponse("400-response-with-errors.json");
        assertThat(exception).isInstanceOf(InvalidRequestException.class);
        assertThat(((InvalidRequestException)exception).getErrors().size()).isEqualTo(1);
        assertThat(exception.getMessage()).isEqualTo("Message 2");
    }

    @Test
    public void given401Response_whenConvert_thenGetUnauthorizedException() throws IOException {
        var exception = getExceptionFromResponse("401-response.json");
        assertThat(exception).isInstanceOf(UnauthenticatedException.class);
        assertThat(exception.getErrorCode()).isEqualTo(40101);
        assertThat(exception.getMessage()).isEqualTo("Invalid Account.");
    }

    @Test
    public void given403Response_whenConvert_thenGetForbiddenException() throws IOException {
        var exception = getExceptionFromResponse("403-response.json");
        assertThat(exception).isInstanceOf(UnauthorizedException.class);
        assertThat(exception.getErrorCode()).isEqualTo(40302);
        assertThat(exception.getMessage()).isEqualTo("Subscription is expired.");
    }

    @Test
    public void given404Response_whenConvert_thenGetResourceNotFoundException() throws IOException {
        var exception = getExceptionFromResponse("404-response.json");
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class);
        assertThat(exception.getErrorCode()).isEqualTo(10001);
        assertThat(exception.getMessage()).isEqualTo("CourseNode (id=1001) is not found.");
    }

    @Test
    public void givenDefault404Response_whenConvert_thenGetResourceNotFoundException() throws IOException {
        var exception = getExceptionFromResponse("default-404-response.json");
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class);
        assertThat(exception.getErrorCode()).isEqualTo(0);
        assertThat(exception.getMessage()).isNull();
    }

    @Test
    public void given409Response_whenConvert_thenGetBusinessConflictException() throws IOException {
        var exception = getExceptionFromResponse("409-response.json");
        assertThat(exception).isInstanceOf(BusinessConflictException.class);
        assertThat(exception.getErrorCode()).isEqualTo(2201);
        assertThat(exception.getMessage()).isEqualTo("Practice is not unlocked.");
    }

    @Test
    public void given500Response_whenConvert_thenGetApiException() throws IOException {
        var exception = getExceptionFromResponse("500-response.json");
        assertThat(exception).isInstanceOf(ApiException.class);
        assertThat(exception.getErrorCode()).isEqualTo(0);
        assertThat(exception.getMessage()).isEqualTo("ApiErrorResponse(path=/api/errors/500, status=500, subStatus=0, error=Internal Server Error, message=Null pointer exception., errors=[])");
    }

    @Test
    public void givenEmptyResponse_whenConvert_thenGetApiException() throws IOException {
        var exception = getExceptionFromResponse("empty-response.json");
        assertThat(exception).isInstanceOf(ApiException.class);
        assertThat(exception.getErrorCode()).isEqualTo(0);
        assertThat(exception.getMessage()).isEqualTo("ApiErrorResponse(path=null, status=0, subStatus=0, error=null, message=null, errors=[])");
    }

    private ApiException getExceptionFromResponse(String filename) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        var response = new ObjectMapper().readValue(file, ApiErrorResponse.class);
        return response.toApiException();
    }
}
