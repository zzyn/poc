package com.webflux.cassandra.demo.core.validator;

import javax.validation.Constraint;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.lang.annotation.*;


/**
 * PlanDate class
 *
 * @author ef edtech
 */
@Documented
@Constraint(validatedBy = PlanDateValidator.class)
@SupportedValidationTarget({ValidationTarget.PARAMETERS})
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PlanDate {

    String message() default "plandate must utc time formate(yyyy-MM-dd'T'HH:mm:ss'Z').";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
    boolean allowNull();
    boolean allowEmpty();

}
