package com.spring.libs.flux.auth;

import com.spring.libs.flux.auth.core.RetrieveAcls;
import com.spring.libs.flux.auth.mock.MockBootstrap;
import com.spring.libs.flux.auth.core.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockBootstrap.class,
    properties = {
        "kt.security.authorized-programs[0]=HFV3Plus",
        "kt.security.authorized-programs[1]=HFV2"
    }
)
public class SecurityControllerTest {
    private static final String TEST_SECURITY_API = "/test/api/security";
    private static final String TEST_ADMIN_SECURITY_API = "/test/admin/api/security";
    private static final String TEST_AUTHORITY_API = "/test/kt-authority/api/security";
    private static final String TEST_ROLE_API = "/test/kt-roles/api/security";

    private static final String VALID_ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJqdGkiOiI0OWU0NWQ0Zi1hYzNlLTQ2NTYtODE1NC00YWRlZjQwNThjMjEiLCJzdWIiOiIxMjM0NTY3IiwiaWF0IjoxNTc5NTEzMzI2LCJleHAiOjEwMjE5NTEzMzI2LCJjb3JyZWxhdGlvbl9pZCI6ImI3ZmEwZTcxLTZiYTUtNDkzOC05ZDc3LThiOGRmYTVlMGZmNSIsInJlZl9pZCI6ImRiNTg5YzI3LTI2OTItNDA2NS05MmM4LThhMjE3MjY3OWE5NyJ9.WyU5oDF56hWn6ZfxnwpWaYNwozCWO4UpG1OXmtrT7x0gT9g5Bsyi7gX43Fi3uxmvOLsxyvu-a1dSt5Mi8ZaNS1wbiGu03ZrOf7ebVUycs5ukx3worYSj7ZPrR_6HSyuzJkJxkQnip7gsF5G52l0r5xXqY79TEZVw73mvBms5QLcjYq9ZNKMf3R9V8a0MGY9QRkeoyUNDn9z51pAI2V4fZAnMw_mFYci16NvemZNz18A2BQPi218XBTcu-85IcYIMv8fEugMm5ffDfYfAQa6YDRmGAdBqzy84QtRgPN9tV2DbHrV1_lj4rY1rJw66kZ1GFTxLBQ1gqKREBNMkrhoi6w";
    private static final String EXPIRED_ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJqdGkiOiI2NDdmOTRmOS1jMTQwLTQ0MjMtOGE0OC0zNDg2N2RkMTNkMDciLCJzdWIiOiIxMjM0NTY3IiwiaWF0IjoxNTc5NTEzNTM1LCJleHAiOjE1Nzk1MTM0NzIsImNvcnJlbGF0aW9uX2lkIjoiYTk0ZWFkNGQtN2Y0Zi00MzRiLWExYTYtNThlNTI3ZDYzZDBiIiwicmVmX2lkIjoiMDY4Y2RkYTktM2MyOS00ZWNkLWE2NGQtYWIzN2FmMjRmYzQyIn0.Cooom8bkCF-bpAeOMjjdlujP-cDM9n53BT1QK1y8Ng3Ay4JXgtUAcwb3ZCQxM1z-Tf--QjQduuzDmIXt3RtZ-txyG_bNCTOgeBaBw3CKAmqm__QLQn2s_sXfWOaeaeTm9Xhbu7CrVEuE-uMZtPWardGQDkqFIy0IFaS_CB92WZeGLXgO8UVgN-3tUjxmwZzj6K-o7Y2DEag6AyzOEhQoXaCF3GWb_1n1U5hiUkL73PpW6eCDGpZsxSN9z3YF_DI8nb9UvXfO1HP37ZAbxdBqesSy50Q2LzofqXIozORA0aAxqETLaVuwawZOsJmKkI7YMSPaFBdr-ztDCW7e0gmcow";
    private static final String INVALID_ACCESS_TOKEN = "abcd";
    private static final String EMPTY_ACCESS_TOKEN = "";

    private WebTestClient webTestClient;

    @Autowired
    private ApplicationContext applicationContext;

    @SpyBean
    private RetrieveAcls retrieveAcls;

    @Before
    public void setup() {
        webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
                .apply(springSecurity())
                .configureClient()
                .build();
    }

    @Test
    public void givenNoToken_whenRequested_thenFailed400() {
        webTestClient.get()
                .uri(TEST_SECURITY_API)
                .header(Constants.HttpConstants.HEADER_ACCESS_TOKEN, EMPTY_ACCESS_TOKEN)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    public void givenValidToken_whenRequested_thenSuccess200() {
        List<Map> hf3Acls = TestUtils.getListDataFromJsonFile("data/hf3-acl.json", Map.class);
        doReturn(Mono.just(hf3Acls)).when(retrieveAcls).execute(anyString());
        webTestClient.get()
                .uri(TEST_SECURITY_API)
                .header(Constants.HttpConstants.HEADER_ACCESS_TOKEN, VALID_ACCESS_TOKEN)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void givenValidToken_ButUnauthorizedProgram_whenRequested_thenAuthorized401() {
        List<Map> fr2Acls = TestUtils.getListDataFromJsonFile("data/fr2-acl.json", Map.class);
        doReturn(Mono.just(fr2Acls)).when(retrieveAcls).execute(anyString());
        webTestClient.get()
                .uri(TEST_SECURITY_API)
                .header(Constants.HttpConstants.HEADER_ACCESS_TOKEN, VALID_ACCESS_TOKEN)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    public void givenExpiredToken_whenRequested_thenAuthorized401() {
        webTestClient.get()
                .uri(TEST_SECURITY_API)
                .header(Constants.HttpConstants.HEADER_ACCESS_TOKEN, EXPIRED_ACCESS_TOKEN)
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    public void givenInvalidToken_whenRequested_thenForbidden403() {
        webTestClient.get()
                .uri(TEST_SECURITY_API)
                .header(Constants.HttpConstants.HEADER_ACCESS_TOKEN, INVALID_ACCESS_TOKEN)
                .exchange()
                .expectStatus()
                .isForbidden();
    }

    @Test
    public void givenValidToken_WrongMethodAuthority_whenRequestAdmin_thenSuccess200() {
        List<Map> hf3Acls = TestUtils.getListDataFromJsonFile("data/hf3-acl.json", Map.class);
        doReturn(Mono.just(hf3Acls)).when(retrieveAcls).execute(anyString());
        webTestClient
                .get()
                .uri(TEST_ADMIN_SECURITY_API)
                .header(Constants.HttpConstants.HEADER_ACCESS_TOKEN, VALID_ACCESS_TOKEN)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    public void givenValidToken_ValidMethodAuthority_whenRequestAdmin_thenSuccess200() {
        List<Map> hf3Acls = TestUtils.getListDataFromJsonFile("data/hf3-acl.json", Map.class);
        doReturn(Mono.just(hf3Acls)).when(retrieveAcls).execute(anyString());
        webTestClient
                .get()
                .uri(TEST_AUTHORITY_API)
                .header(Constants.HttpConstants.HEADER_ACCESS_TOKEN, VALID_ACCESS_TOKEN)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void givenValidToken_ValidMethodRole_whenRequestAdmin_thenSuccess200() {
        List<Map> hf2Acls = TestUtils.getListDataFromJsonFile("data/hf2-acl.json", Map.class);
        doReturn(Mono.just(hf2Acls)).when(retrieveAcls).execute(anyString());
        webTestClient
                .get()
                .uri(TEST_ROLE_API)
                .header(Constants.HttpConstants.HEADER_ACCESS_TOKEN, VALID_ACCESS_TOKEN)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void givenValidTokenInCookie_ValidMethodAuthority_whenRequestAdmin_thenSuccess200() {
        List<Map> hf3Acls = TestUtils.getListDataFromJsonFile("data/hf3-acl.json", Map.class);
        doReturn(Mono.just(hf3Acls)).when(retrieveAcls).execute(anyString());
        webTestClient
                .get()
                .uri(TEST_AUTHORITY_API)
                .cookie(Constants.HttpConstants.HEADER_ACCESS_TOKEN, VALID_ACCESS_TOKEN)
                .exchange()
                .expectStatus().isOk();
    }
}