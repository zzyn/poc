package io.example.restsdk;

import com.spring.libs.flux.exception.utils.ResponseConverter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ErrorsClient {

    private WebClient webClient;

    public ErrorsClient(String baseUrl) {
        webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono<Object> getCustom400Error() {
        return webClient.get().uri("/api/errors/400/custom")
                .exchange()
                .flatMap(resp -> ResponseConverter.toResult(resp, Object.class));
    }

    public Mono<Object> getDefault400Error() {
        return webClient.get().uri("/api/errors/400/default")
                .exchange()
                .flatMap(resp -> ResponseConverter.toResult(resp, Object.class));
    }

    public Mono<Object> getCustom401Error() {
        return webClient.get().uri("/api/errors/401")
                .exchange()
                .flatMap(resp -> ResponseConverter.toResult(resp, Object.class));
    }

    public Mono<Object> getCustom403Error() {
        return webClient.get().uri("/api/errors/403")
                .exchange()
                .flatMap(resp -> ResponseConverter.toResult(resp, Object.class));
    }

    public Mono<Object> getCustom404Error() {
        return webClient.get().uri("/api/errors/404")
                .exchange()
                .flatMap(resp -> ResponseConverter.toResult(resp, Object.class));
    }

    public Mono<Object> getDefault404Error() {
        return webClient.get().uri("/api/errors/nonexist")
                .exchange()
                .flatMap(resp -> ResponseConverter.toResult(resp, Object.class));
    }

    public Mono<Object> getCustom409Error() {
        return webClient.get().uri("/api/errors/409")
                .exchange()
                .flatMap(resp -> ResponseConverter.toResult(resp, Object.class));
    }

    public Mono<Object> getDefault500Error() {
        return webClient.get().uri("/api/errors/500")
                .exchange()
                .flatMap(resp -> ResponseConverter.toResult(resp, Object.class));
    }

    public Mono<Object> getDefault504Error() {
        return webClient.get().uri("/api/errors/504")
                .exchange()
                .flatMap(resp -> ResponseConverter.toResult(resp, Object.class));
    }
}
