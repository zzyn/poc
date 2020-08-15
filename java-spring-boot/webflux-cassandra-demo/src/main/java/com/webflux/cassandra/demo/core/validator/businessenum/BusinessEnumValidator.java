package com.webflux.cassandra.demo.core.validator.businessenum;

import com.webflux.cassandra.demo.core.utility.PlanUtility;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BusinessEnumValidator implements ConstraintValidator<BusinessEnumMessage,Integer> {
    private Class selectedClass;

    @Override
    public void initialize(BusinessEnumMessage key) {
        selectedClass =key.enumClass();
    }

    @Override
    public boolean isValid(Integer originalCode, ConstraintValidatorContext constraintValidatorContext) {
        Integer code = Integer.valueOf(originalCode);
        if(!selectedClass.isEnum()&&!selectedClass.isInstance(CustomEnum.class.getName())){
            return false;
        }
        return PlanUtility.isInList(code, selectedClass);
    }
}
