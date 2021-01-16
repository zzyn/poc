package com.spring.libs.mvc.auth.service.impl;

import com.spring.libs.mvc.auth.model.JwtContext;
import com.spring.libs.mvc.auth.service.IdTokenDbValidateService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdTokenDbValidateServiceDefaultImpl implements IdTokenDbValidateService {

    public IdTokenDbValidateServiceDefaultImpl() {

    }

    @Override
    public void validate(JwtContext jwt) throws Exception {
        // TODO
        log.warn("id token db validator service is not implement");
    }
}
