package com.spring.libs.flux.auth;

import com.spring.libs.flux.auth.core.RetrieveAcls;
import com.spring.libs.flux.auth.mock.MockBootstrap;
import com.spring.libs.flux.auth.core.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockBootstrap.class,
        properties = {
            "kt.security.disable-authorization=true",
            "kt.security.authorization-uri=''"
        })
@AutoConfigureWebTestClient
public class DisableAuthorizationTest {
    private static final String TEST_SECURITY_API = "/test/api/security";

    @Autowired
    private WebTestClient webTestClient;

    @SpyBean
    private RetrieveAcls retrieveAcls;

    private static final String VALID_ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJqdGkiOiI0OWU0NWQ0Zi1hYzNlLTQ2NTYtODE1NC00YWRlZjQwNThjMjEiLCJzdWIiOiIxMjM0NTY3IiwiaWF0IjoxNTc5NTEzMzI2LCJleHAiOjEwMjE5NTEzMzI2LCJjb3JyZWxhdGlvbl9pZCI6ImI3ZmEwZTcxLTZiYTUtNDkzOC05ZDc3LThiOGRmYTVlMGZmNSIsInJlZl9pZCI6ImRiNTg5YzI3LTI2OTItNDA2NS05MmM4LThhMjE3MjY3OWE5NyJ9.WyU5oDF56hWn6ZfxnwpWaYNwozCWO4UpG1OXmtrT7x0gT9g5Bsyi7gX43Fi3uxmvOLsxyvu-a1dSt5Mi8ZaNS1wbiGu03ZrOf7ebVUycs5ukx3worYSj7ZPrR_6HSyuzJkJxkQnip7gsF5G52l0r5xXqY79TEZVw73mvBms5QLcjYq9ZNKMf3R9V8a0MGY9QRkeoyUNDn9z51pAI2V4fZAnMw_mFYci16NvemZNz18A2BQPi218XBTcu-85IcYIMv8fEugMm5ffDfYfAQa6YDRmGAdBqzy84QtRgPN9tV2DbHrV1_lj4rY1rJw66kZ1GFTxLBQ1gqKREBNMkrhoi6w";

    @Test
    public void givenValidToken_UnauthorizedProgram_AuthorizationDisabled_whenRequested_thenOK200() {
        List<Map> fr2Acls = TestUtils.getListDataFromJsonFile("data/fr2-acl.json", Map.class);
        doReturn(Mono.just(fr2Acls)).when(retrieveAcls).execute(anyString());
        webTestClient.get()
                .uri(TEST_SECURITY_API)
                .header(Constants.HttpConstants.HEADER_ACCESS_TOKEN, VALID_ACCESS_TOKEN)
                .exchange()
                .expectStatus().isOk();
    }
}
