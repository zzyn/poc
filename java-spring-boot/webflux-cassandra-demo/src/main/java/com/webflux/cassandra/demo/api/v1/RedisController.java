package com.webflux.cassandra.demo.api.v1;

import com.webflux.cassandra.demo.core.cache.RedisService;
import com.webflux.cassandra.demo.domain.entity.Plan;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Api
@RequestMapping("/api/v2/redis")
@RestController
public class RedisController {

    private final RedisService redisService;

    @Autowired
    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/save",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ApiOperation(value = "save value")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "UnAuthorized"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public Mono<Boolean> save() throws Exception {

        Plan p = new Plan();
        p.setId(UUID.randomUUID());
        p.setPlanKey("plan_123");
        return Mono.justOrEmpty(redisService.set("plan_123", p));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/saveset",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ApiOperation(value = "save set")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "UnAuthorized"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public Mono<Boolean> saveSet() throws Exception {

        Plan p1 = new Plan();
        p1.setId(UUID.randomUUID());
        p1.setPlanKey("plan_1");
        Plan p2 = new Plan();
        p2.setId(UUID.randomUUID());
        p2.setPlanKey("plan_2");
        List<Plan> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        return Mono.justOrEmpty(redisService.setSet("plan_456", list));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/get",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ApiOperation(value = "get value")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "UnAuthorized"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public Mono<Plan> get() throws Exception {

        return Mono.justOrEmpty(redisService.get("plan_123", Plan.class));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getset",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ApiOperation(value = "get set")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "UnAuthorized"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public Flux<Plan> getset() throws Exception {

        return Flux.fromStream(redisService.getSet("plan_456", Plan.class).stream());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/delete",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ApiOperation(value = "delete value")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "UnAuthorized"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public Mono<Boolean> delete() throws Exception {

        return Mono.justOrEmpty(redisService.delete("plan_456"));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/exist",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ApiOperation(value = "has key")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "UnAuthorized"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public Mono<Boolean> hasKey() throws Exception {

        return Mono.justOrEmpty(redisService.hasKey("plan_123"));
    }

}
