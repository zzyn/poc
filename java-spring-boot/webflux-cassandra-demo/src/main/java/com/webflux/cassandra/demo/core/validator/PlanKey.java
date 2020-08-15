package com.webflux.cassandra.demo.core.validator;

import javax.validation.Constraint;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.lang.annotation.*;

/**
 * PlanKey class
 *
 * @author ef edtech
 */
@Documented
@Constraint(validatedBy = PlanKeyValidator.class)
@SupportedValidationTarget({ValidationTarget.PARAMETERS})
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PlanKey {

    String message() default "PlanBusinessKey must contain at least 1 characters, one digit, a lower case letter, an upper case letter, and a special character.";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

    int min();
    int max();
    boolean allowNull();
    boolean allowEmpty();
    boolean applyPattern();

}


