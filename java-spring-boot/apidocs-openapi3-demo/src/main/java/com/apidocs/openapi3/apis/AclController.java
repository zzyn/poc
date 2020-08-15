package com.apidocs.openapi3.apis;

import com.apidocs.openapi3.errors.ApiErrorDto;
import com.apidocs.openapi3.dto.AclModelDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Tags(@Tag(name = "acl", description = "access control list"))
@RestController
@RequestMapping("/api/v1/acl")
public class AclController {

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/products",
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @Operation(
            parameters = {
                    @Parameter(name = "X-EF-ID", in = ParameterIn.HEADER, description = "id token", required = true),
            },
            responses = {
                    @ApiResponse(responseCode = "200"
                            , description = "OK"
                            , content = {@Content(array = @ArraySchema(schema = @Schema(implementation = AclModelDto.class)))}
                    ),
                    @ApiResponse(responseCode = "400"
                            , description = "BAD REQUEST"
                            , content = {@Content(schema = @Schema(implementation = ApiErrorDto.class))}
                    ),
                    @ApiResponse(responseCode = "401"
                            , description = "UNAUTHORIZED"
                            , content = {@Content(schema = @Schema(implementation = ApiErrorDto.class))}
                    ),
                    @ApiResponse(responseCode = "500"
                            , description = "INTERNAL SERVER ERROR"
                            , content = {@Content(schema = @Schema(implementation = ApiErrorDto.class))}
                    ),
            }
    )
    public List<AclModelDto> getProducts() {
        AclModelDto aclModelDto = new AclModelDto();
        aclModelDto.setActived(true);
        return Collections.singletonList(aclModelDto);
    }
}
