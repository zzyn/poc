package com.spring.libs.mvc.auth.config;

import org.springframework.util.StringUtils;

public class AuthConfig {

    private String[] pathPatterns;

    public AuthConfig(String[] pathPatterns) {
        this.pathPatterns = StringUtils.trimArrayElements(pathPatterns);
    }

    public String[] getPathPatterns() {
        return pathPatterns;
    }
}
