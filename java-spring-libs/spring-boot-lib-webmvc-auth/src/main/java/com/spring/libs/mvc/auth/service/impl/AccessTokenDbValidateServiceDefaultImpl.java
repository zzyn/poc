package com.spring.libs.mvc.auth.service.impl;

import com.spring.libs.mvc.auth.model.JwtContext;
import com.spring.libs.mvc.auth.service.AccessTokenDbValidateService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessTokenDbValidateServiceDefaultImpl implements AccessTokenDbValidateService {

    public AccessTokenDbValidateServiceDefaultImpl() {

    }

    @Override
    public void validate(JwtContext jwt) throws Exception {
        // TODO
        log.warn("access token db validator service is not implement");
    }
}
