package com.apidocs.openapi3.apis;

import com.apidocs.openapi3.dto.AuthRequestDto;
import com.apidocs.openapi3.dto.AuthResponseDto;
import com.apidocs.openapi3.errors.ApiErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Tags(@Tag(name = "auth", description = "login and logout"))
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @Operation(
            responses = {
                    @ApiResponse(responseCode = "200"
                            , description = "OK"
                            , content = {@Content(schema = @Schema(implementation = AuthResponseDto.class))}
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
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        AuthResponseDto dto = new AuthResponseDto();
        return dto;
    }
}
