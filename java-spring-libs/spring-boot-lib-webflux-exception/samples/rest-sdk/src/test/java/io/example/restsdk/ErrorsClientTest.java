package io.example.restsdk;

import com.spring.libs.flux.exception.exceptions.*;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.web.client.HttpServerErrorException;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ErrorsClientTest {

    @ClassRule
    public static WireMockClassRule wiremock = new WireMockClassRule(options().port(8080));

    private ErrorsClient errorClient;

    @Before
    public void setup() {
        errorClient = new ErrorsClient("http://localhost:8080");
    }

    @Test
    public void testGetCustom400Error() {
        assertThatExceptionOfType(InvalidRequestException.class).isThrownBy(() -> {
            errorClient.getCustom400Error().block();
        });
    }

    @Test
    public void testGetDefault400Error() {
        assertThatExceptionOfType(InvalidRequestException.class).isThrownBy(() -> {
            errorClient.getDefault400Error().block();
        });
    }

    @Test
    public void testGetCustom401Error() {
        assertThatExceptionOfType(UnauthenticatedException.class).isThrownBy(() -> {
            errorClient.getCustom401Error().block();
        });
    }

    @Test
    public void testGetCustom403Error() {
        assertThatExceptionOfType(UnauthorizedException.class).isThrownBy(() -> {
            errorClient.getCustom403Error().block();
        });
    }

    @Test
    public void testGetCustom404Error() {
        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> {
            errorClient.getCustom404Error().block();
        });
    }

    @Test
    public void testGetDefault404Error() {
        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> {
            errorClient.getDefault404Error().block();
        });
    }

    @Test
    public void testGetCustom409Error() {
        assertThatExceptionOfType(BusinessConflictException.class).isThrownBy(() -> {
            errorClient.getCustom409Error().block();
        });
    }

    @Test
    public void testGetDefault500Error() {
        assertThatExceptionOfType(ApiException.class).isThrownBy(() -> {
            errorClient.getDefault500Error().block();
        });
    }

    @Test
    public void testGetDefault504Error() {
        assertThatExceptionOfType(HttpServerErrorException.class).isThrownBy(() -> {
            errorClient.getDefault504Error().block();
        });
    }
}
