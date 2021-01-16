package com.spring.libs.mvc.auth.service;

import com.spring.libs.mvc.auth.model.JwtContext;

public interface RefreshTokenDbValidateService {

    void validate(JwtContext jwt) throws Exception;
}
