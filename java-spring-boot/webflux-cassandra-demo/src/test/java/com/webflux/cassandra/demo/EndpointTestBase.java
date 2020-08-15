package com.webflux.cassandra.demo;


import com.webflux.cassandra.demo.core.bind.JwtContextArgumentResolver;
import com.webflux.cassandra.demo.core.config.KeyConfig;
import com.webflux.cassandra.demo.domain.dto.PlanDto;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;



public class EndpointTestBase {

    @LocalServerPort
    protected String serverPort;
    @Autowired
    protected ResourceLoader resourceLoader;
    @Autowired
    protected KeyConfig keyConfig;
    @Autowired
    private ReactiveCassandraTemplate cassandraTemplate;
    @Autowired
    protected JwtContextArgumentResolver jwtContextArgumentResolver;

    protected String token;
    protected WebTestClient webTestClient;
    protected String baseUrlTemplate = "http://localhost:%s/api/v1/";
    protected Duration timeoutDuration = Duration.ofMinutes(3);
    private final String MockToken="eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhY2Nlc3MtdG9rZW4iLCJhcHBfaWQiOiI2MDEyNDc0Ny0wZDg3LTQ0ZDItOWI0Yy0zODMzN2NlZDZiNmYiLCJhcHBfbmFtZSI6InBsYXRmb3JtLXBsYW4tc3ZjIiwidHlwZSI6InNlcnZpY2UiLCJyb2xlcyI6bnVsbCwiYWNscyI6bnVsbCwiaWF0IjoxNTYzMjEzNDY4LCJleHAiOjQ3MTg4ODcwNjh9.mh17_pPXb1NQwK3Et3Z-vomdtps3Hao4FNb7d0qTfWfAunPbhqYIkJvKNJF2icEs98DOLwj0nsJcQUkwjH1SvAa6bnmg9DWD63aCzTS7VvzUpPjlIOMMpwPWca9uuZrij8p8kU-BXEn_o0w20_OzAO_AGMfCEMgBAHEekZhNW6-mauj7oOAMLcYnpaO_7tJjbFIyWs2uxSG2cF9Wza_TMb3jINY0Wl3DRVv2nkCVSnoQ2zS1bNhgJ5A9oV1Mtzsr7Uv235qH_ij-mkCpe2bJ6u4d3ey-_1dHmJmcUdU0cIFsGddvrA6UtKoyaDBXkcZcFdzc1TYrIhiSJ7iKOFKOuw";
    public EndpointTestBase() {
    }

    protected String getJwtToken() {
        return MockToken;
    }

    protected WebTestClient.Builder getBuilderWithInjectControllers(MediaType consumerMediaType, MediaType producerMediaType, Object... controllers) throws Exception {

        return WebTestClient
                .bindToController(controllers)
                .configureClient()
                .defaultHeader(HttpHeaders.ACCEPT, producerMediaType.toString())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, consumerMediaType.toString())
                .responseTimeout(timeoutDuration)
                .baseUrl(String.format(baseUrlTemplate, serverPort));

    }

    public WebTestClient.Builder getDefaultBuilder() throws Exception {

        return WebTestClient
                .bindToServer()
                .responseTimeout(timeoutDuration)
                .baseUrl(String.format(baseUrlTemplate, serverPort));
    }

    public WebTestClient.Builder getEmptyIdentityTokenBuilder() {

        return WebTestClient
                .bindToServer()
                .responseTimeout(timeoutDuration)
                .baseUrl(String.format(baseUrlTemplate, serverPort));
    }

    public WebTestClient.Builder getInValidIdentityTokenBuilder() {

        return WebTestClient
                .bindToServer()
                .responseTimeout(timeoutDuration)
                .baseUrl(String.format(baseUrlTemplate, serverPort));
    }

    public  <T> FluxExchangeResult<T> getFluxExchangeResultWithOutRequestBody(HttpMethod httpMethod
            , WebTestClient.Builder webTestClientBuilder
            , Class<T> responseClazz
            , String urlTemplate
            , Object... uriVars) {

        return webTestClientBuilder
                .build()
                .method(httpMethod)
                .uri(urlTemplate, uriVars)
                .exchange()
                .returnResult(responseClazz);

    }

    public  <S, T> FluxExchangeResult<T> getFluxExchangeResultWithRequestBody(HttpMethod httpMethod
            , WebTestClient.Builder webTestClientBuilder
            , Class<S> requestClazz
            , Publisher<S> requestPublisher
            , Class<T> responseClazz
            , String urlTemplate
            , Object... uriVars) {

        return webTestClientBuilder
                .build()
                .method(httpMethod)
                .uri(urlTemplate, uriVars)
                .body(requestPublisher, requestClazz)
                .exchange()
                .returnResult(responseClazz);
    }

    public  <T extends Serializable> List<T> getListResult(FluxExchangeResult<T> fluxResult) {
        return fluxResult
                .getResponseBody()
                .collectList()
                .block();
    }

    public  <T extends Serializable> PlanDto getSingleResult(FluxExchangeResult<PlanDto> fluxResult) {
        return fluxResult
                .getResponseBody()
                .toStream()
                .findFirst()
                .get();
    }
}
