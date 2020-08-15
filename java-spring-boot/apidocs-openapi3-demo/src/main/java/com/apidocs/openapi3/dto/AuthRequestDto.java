package com.apidocs.openapi3.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;


@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthRequestDto {

    @Schema(required = true)
    @JsonProperty("userName")
    private String userName;
    @Schema(required = true)
    @JsonProperty("password")
    private String password;
}
