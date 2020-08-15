package com.webflux.cassandra.demo.core.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

/**
 * @author ef edtech
 */
public class ValidatorUtil {
    private static Validator validate=Validation.buildDefaultValidatorFactory()
            .getValidator();
    private   ValidatorUtil()
    {
    }
    public static <T> Map<String,StringBuffer> validate(T obj){
        Map<String,StringBuffer> errorMap = null;
        Set<ConstraintViolation<T>> set = validate.validate(obj,Default.class);
        if(set != null && !set.isEmpty() ){
            errorMap = new HashMap<>(10);
            String property;
            for(ConstraintViolation<T> cv : set){
                property = cv.getPropertyPath().toString();
                if(errorMap.get(property) != null){
                    errorMap.get(property).append(",").append(cv.getMessage());
                }else{
                    StringBuffer sb = new StringBuffer();
                    sb.append(cv.getMessage());
                    errorMap.put(property, sb);
                }
            }
        }
        return errorMap;
    }
}
