package ${groupId}.app.api;

import ${groupId}.contract.${entity}Model;
import ${groupId}.core.bind.Jwt;
import ${groupId}.core.entity.JwtContext;
import ${groupId}.domain.shared.${entity}Domain;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import springfox.documentation.annotations.ApiIgnore;


import java.util.List;

import static ${groupId}.core.CoreConstants.ID_TOKEN_DESC;
import static ${groupId}.core.CoreConstants.ID_TOKEN_HEADER;

@RestController
@RequestMapping("/api/v1/${entity_camel}")
public class ${entity}Controller {

    private final ${entity}Domain ${entity_camel}Domain;

    @Autowired
    public ${entity}Controller(${entity}Domain ${entity_camel}Domain) {
        this.${entity_camel}Domain = ${entity_camel}Domain;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get ${entity_camel}")
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = ID_TOKEN_HEADER, value = ID_TOKEN_DESC, required = true, paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ${entity}Model.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "UnAuthorized"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public Mono<${entity}Model> get(@ApiIgnore @Jwt JwtContext context, @ApiIgnore ServerWebExchange serverWebExchange) throws Exception {

        return Mono.empty();
    }
}