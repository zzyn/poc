package com.webflux.cassandra.demo.core.validator;


import com.webflux.cassandra.demo.core.utility.PlanUtility;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.webflux.cassandra.demo.domain.Constants.KEY_VALIDATOR;

/**
 * PlanKeyValidator class
 *
 * @author ef edtech
 */
public class PlanKeyValidator implements ConstraintValidator<PlanKey, String> {


    private Pattern pattern;
    private int max=1024;
    private int min=0;
    private boolean allowNull=true;
    private boolean allowEmpty=true;
    private boolean applyPattern=true;
    @Override
    public void initialize(PlanKey key) {
        max=key.max();
        min=key.min();
        allowEmpty=key.allowEmpty();
        allowNull=key.allowNull();
        pattern = Pattern.compile(KEY_VALIDATOR);
        applyPattern=key.applyPattern();

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
        if (applyPattern) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                return validateLength(s);
            }
            return false;
        }
        return validateLength(s);
    }
    private Boolean validateLength(String originalString) {
        if (PlanUtility.hasStringValue(originalString)) {
            return originalString.length() <= max && originalString.length() >= min;
        }
        return false;
    }
}
