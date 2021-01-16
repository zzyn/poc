package com.spring.libs.mvc.auth.service.impl;

import com.spring.libs.mvc.auth.model.JwtContext;
import com.spring.libs.mvc.auth.service.RefreshTokenDbValidateService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RefreshTokenDbValidateServiceDefaultImpl implements RefreshTokenDbValidateService {

    public RefreshTokenDbValidateServiceDefaultImpl() {

    }

    @Override
    public void validate(JwtContext jwt) throws Exception {
        // TODO
        log.warn("refresh token db validator service is not implement");
    }
}
