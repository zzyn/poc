package com.spring.libs.flux.exception.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiErrorResponseDeserializTest {

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
    }

    private File getFile(String filename) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        return file;
    }

    @Test
    public void givenDefault400Response_whenDeserialize_thenSubStatusIs0() throws IOException {
        File file = getFile("400-response.json");
        var response = mapper.readValue(file, ApiErrorResponse.class);
        assertThat(response.getSubStatus()).isEqualTo(0);
        assertThat(response.getErrors()).isEmpty();
    }

    @Test
    public void givenCustom400Response_whenDeserialize_thenSubStatusIs1001() throws IOException {
        File file = getFile("400-response-with-sub-status.json");
        var response = mapper.readValue(file, ApiErrorResponse.class);
        assertThat(response.getSubStatus()).isEqualTo(1001);
        assertThat(response.getErrors()).isEmpty();
    }

    @Test
    public void givenSpring400Response_whenDeserialize_thenIncludeErrors() throws IOException {
        File file = getFile("400-response-with-errors.json");
        var response = mapper.readValue(file, ApiErrorResponse.class);
        assertThat(response.getSubStatus()).isEqualTo(0);
        assertThat(response.getErrors().size()).isEqualTo(1);
    }

    @Test
    public void givenEmptyResponse_whenDeserialize_thenGetResponseWithDefaultValue() throws IOException {
        File file = getFile("empty-response.json");
        var response = mapper.readValue(file, ApiErrorResponse.class);
        assertThat(response.getStatus()).isEqualTo(0);
        assertThat(response.getSubStatus()).isEqualTo(0);
        assertThat(response.getMessage()).isNull();
        assertThat(response.getErrors()).isEmpty();
    }
}
