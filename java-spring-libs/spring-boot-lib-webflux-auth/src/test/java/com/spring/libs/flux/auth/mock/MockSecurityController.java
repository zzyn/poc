package com.spring.libs.flux.auth.mock;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MockSecurityController {

    @GetMapping("/test/api/security")
    public Mono<String> info() {
        return Mono.just("Auth Succeed");
    }

    @GetMapping("/test/admin/api/security")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<String> adminInfo() {
        return Mono.just("Hello security.");
    }

    @GetMapping("/test/kt-authority/api/security")
    // hasAnyAuthority means use authority, and it should be exactly same as authority.
    @PreAuthorize("hasAnyAuthority('ROLE_HFV3Plus', 'SSV3', 'TBV3')")
    public Mono<String> ktAuthority() {
        return Mono.just("Hello security.");
    }

    @GetMapping("/test/kt-roles/api/security")
    // hasAnyRole means use role, and by default the value will be
    // added a prefix 'ROLE_' and then compare with authority.
    @PreAuthorize("hasAnyRole('HFV2', 'SSV3', 'ROLE_TBV3')")
    public Mono<String> ktRoleInfo() {
        return Mono.just("Hello security.");
    }
}
