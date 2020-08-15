package com.webflux.cassandra.demo.core.validator.businessenum;

import javax.validation.Constraint;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BusinessEnumValidator.class)
@SupportedValidationTarget({ValidationTarget.PARAMETERS})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessEnumMessage {
    String message() default "This value is not in the value defined by the enumeration class.";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

    Class enumClass();
}
