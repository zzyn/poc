package com.spring.libs.flux.exception.autoconfigure;

import com.spring.libs.flux.exception.autoconfigure.sync.ErrorHandlerAutoConfigurationSync;
import com.spring.libs.flux.exception.autoconfigure.webflux.ErrorHandlerAutoConfigurationAsync;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(value = {ErrorHandlerAutoConfigurationAsync.class,
                ErrorHandlerAutoConfigurationSync.class})
public @interface EnableErrorHandler {
}
