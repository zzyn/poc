package io.example.restapi;

import lombok.val;
import com.spring.libs.flux.exception.exceptions.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("/api/errors")
public class ErrorsController {

    @GetMapping("/400/{id}")
    public Mono<Object> throwDefaultInvalidRequestException(@PathVariable int id) {
        return Mono.just(id);
    }

    @GetMapping("/400/custom")
    public Mono<Object> throwInvalidRequestException() {

        val errors = Arrays.asList(
            new HashMap<String, String>(){{
                put("field", "studentId");
                put("rejectValue", "abcdef");
                put("message", "should be integer");
            }},
            new HashMap<String, String>(){{
                put("field", "planSystemKey");
                put("rejectValue", "489d8bd6-347c-4509-a963-a2b47cafef75");
                put("message", "should be time UUID");
            }});

        return Mono.error(new InvalidRequestException(1001, "Input parameters invalid.", errors));

    }

    @GetMapping("/401")
    public Mono<Object> throw401() {
        return Mono.error(new UnauthenticatedException(40101, "Invalid account."));
    }

    @GetMapping("/403")
    public Mono<Object> throw403() {
        return Mono.error(new UnauthorizedException(40302, "Subscription is expired."));
    }

    @GetMapping("/404")
    public Mono<Object> throw404() {
        return Mono.error(new ResourceNotFoundException(10001, "CourseNode (id=15) not found."));
    }

    @GetMapping("/409")
    public Mono<Object> throw409() {
        return Mono.error(new BusinessConflictException(2201, "Practice is not unlocked."));
    }

    @GetMapping("/500")
    public Mono<Object> throwOtherException() {
        try {
           String lang = null;
           Boolean endWithA = lang.endsWith("a");  //here will throw NPE.
           return Mono.just(endWithA);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    @GetMapping("/501")
    public Mono<Object> throwNotImplemented() {
        return Mono.error(new UnsupportedOperationException());
    }


}
