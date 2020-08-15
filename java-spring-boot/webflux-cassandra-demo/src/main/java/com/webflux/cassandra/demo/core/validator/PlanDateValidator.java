package com.webflux.cassandra.demo.core.validator;


import com.webflux.cassandra.demo.core.utility.PlanUtility;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * PlanDateValidator class
 *
 * @author ef edtech
 */
public class PlanDateValidator implements ConstraintValidator<PlanDate, String> {
    private boolean allowNull=true;
    private boolean allowEmpty=true;
    @Override
    public void initialize(PlanDate key) {
        allowEmpty=key.allowEmpty();
        allowNull=key.allowNull();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (allowNull && s == null) {
            return true;
        }
        if (allowEmpty && "".equals(s)) {
            return true;
        }
        if (s == null || "".equals(s)) {
            return false;
        }
        return PlanUtility.validateTimestampFormat(s);
    }
}
