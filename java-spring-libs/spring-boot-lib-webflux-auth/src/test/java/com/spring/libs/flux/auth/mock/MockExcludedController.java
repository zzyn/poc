package com.spring.libs.flux.auth.mock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/test/api/excluded")
public class MockExcludedController {
    @GetMapping
    public Mono<String> helloExclude() {
        return Mono.just("I'm excluded");
    }
}
