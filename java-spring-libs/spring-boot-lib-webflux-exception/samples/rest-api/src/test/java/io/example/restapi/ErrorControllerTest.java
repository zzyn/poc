package io.example.restapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class ErrorControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testDefault400() {
        webTestClient.get()
                .uri("/api/errors/400/default")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.status").isEqualTo(400);
    }

    @Test
    public void testCustom400() {
        webTestClient.get()
                .uri("/api/errors/400/custom")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.status").isEqualTo(400)
                .jsonPath("$.subStatus").isEqualTo(1001);
    }

    @Test
    public void testCustom401() {
        webTestClient.get()
                .uri("/api/errors/401")
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody()
                .jsonPath("$.status").isEqualTo(401)
                .jsonPath("$.subStatus").isEqualTo(40101);
    }

    @Test
    public void testCustom403() {
        webTestClient.get()
                .uri("/api/errors/403")
                .exchange()
                .expectStatus().isForbidden()
                .expectBody()
                .jsonPath("$.status").isEqualTo(403)
                .jsonPath("$.subStatus").isEqualTo(40302);
    }

    @Test
    public void testDefault404() {
        webTestClient.get()
                .uri("/api/errors/nonexist")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.status").isEqualTo(404);
    }

    @Test
    public void testCustom404() {
        webTestClient.get()
                .uri("/api/errors/404")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.status").isEqualTo(404)
                .jsonPath("$.subStatus").isEqualTo(10001);
    }

    @Test
    public void test409() {
        webTestClient.get()
                .uri("/api/errors/409")
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody()
                .jsonPath("$.status").isEqualTo(409)
                .jsonPath("$.subStatus").isEqualTo(2201);
    }

    @Test
    public void test500() {
        webTestClient.get()
                .uri("/api/errors/500")
                .exchange()
                .expectStatus().isEqualTo(500)
                .expectBody()
                .jsonPath("$.status").isEqualTo(500);
    }
}
