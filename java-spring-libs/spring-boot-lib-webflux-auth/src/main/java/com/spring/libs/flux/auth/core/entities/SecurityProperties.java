package com.spring.libs.flux.auth.core.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ConfigurationProperties(prefix = "kt.security")
@Getter
@Setter
@NoArgsConstructor
@Validated
public class SecurityProperties {
    private List<String> authorizedPrograms;
    @NotNull
    private boolean disableAuthorization = false;
    private String authorizationUri;
    @NotBlank
    private String publicKey;
    private List<String> excludePaths;
}
