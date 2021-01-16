package com.spring.libs.flux.auth;

import com.spring.libs.flux.auth.mock.MockBootstrap;
import com.spring.libs.flux.auth.core.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockBootstrap.class,
        properties = {
            "kt.security.authorized-programs[0]=HFV3Plus",
            "kt.security.authorized-programs[1]=HFV2",
            "kt.security.exclude-paths[0]=/test/api/excluded",
            "kt.security.exclude-paths[1]=/test/api/security"
        })
@AutoConfigureWebTestClient
public class ExcludedControllerTest {
    private static String TEST_EXCLUDED_API = "/test/api/excluded";
    private static final String TEST_SECURITY_API = "/test/api/security";

    private static String EMPTY_ACCESS_TOKEN = "";
    private static final String INVALID_ACCESS_TOKEN = "abcd";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void givenNoToken_whenRequestedExcluded_thenSuccess200() {
        webTestClient.get()
                .uri(TEST_EXCLUDED_API)
                .header(Constants.HttpConstants.HEADER_ACCESS_TOKEN, EMPTY_ACCESS_TOKEN)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void givenInvalidToken_whenRequestedExcluded_thenSuccess200() {
        webTestClient.get()
                .uri(TEST_SECURITY_API)
                .header(Constants.HttpConstants.HEADER_ACCESS_TOKEN, INVALID_ACCESS_TOKEN)
                .exchange()
                .expectStatus()
                .isOk();
    }
}
