package com.spring.libs.mvc.auth.aspect;

import com.spring.libs.mvc.auth.model.JwtContext;
import com.spring.libs.mvc.auth.service.AccessTokenDbValidateService;
import com.spring.libs.mvc.auth.utils.AspectUtils;
import com.spring.libs.mvc.exception.ApiErrorCode;
import com.spring.libs.mvc.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Aspect
@Component
public class AccessTokenDbValidatorAspect {

    @Autowired(required = false)
    AccessTokenDbValidateService accessTokenDbValidateService;

    @Pointcut("@annotation(com.ef.edtech.libs.mvc.auth.annotation.AccessTokenDbValidator)")
    public void executeService() {
    }

    @Before(value = "executeService()")
    public void doBeforeAdvice(JoinPoint joinPoint) throws Exception {

        if (AspectUtils.isOptionsRequest(joinPoint)) {
            return;
        }

        Optional<Object> optional = Arrays.stream(joinPoint.getArgs()).filter(n -> n instanceof JwtContext).findAny();

        if (!optional.isPresent()) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ApiErrorCode.APP, "jwt context is null");
        }

        if (Objects.nonNull(accessTokenDbValidateService)) {
            accessTokenDbValidateService.validate((JwtContext) optional.get());
        }
    }
}
