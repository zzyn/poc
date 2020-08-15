package com.webflux.cassandra.demo.domain.model;

import com.webflux.cassandra.demo.core.validator.PlanKey;
import com.webflux.cassandra.demo.core.validator.businessenum.BusinessEnumMessage;
import com.webflux.cassandra.demo.domain.model.businessenum.ProductIdEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

import static com.webflux.cassandra.demo.domain.Constants.*;


/**
 * PlanQueryModel class
 *
 * @author ef edtech
 */
@Data
@AllArgsConstructor
public class PlanQueryModel{

    @BusinessEnumMessage(enumClass = ProductIdEnum.class, message = PLAN_TYPE_ERROR_CODE)
    private
    int productId;

    @PlanKey(min = PLAN_BUSINESS_KEY_MIN_LENGTH, max = PLAN_BUSINESS_KEY_MAX_LENGTH, message = PLAN_BUSINESS_KEY_LENGTH_ERROR_CODE,allowEmpty =false, allowNull = false, applyPattern = false)
    String planBusinessKey;

    @Min(value =BUCKET_ID_DEFAULT_VALUE,message = BUCKET_ID_MIN_ERROR_CODE)
    @Max(value = BUCKET_ID_MAX_LENGTH,message = BUCKET_ID_MAX_ERROR_CODE )
    int bucketId;

    @PlanKey( message = STUDENT_KEY_LENGTH_ERROR_CODE, max = STUDENT_KEY_MAX_LENGTH, min = STUDENT_KEY_MIN_LENGTH,allowNull = true, allowEmpty = true, applyPattern = false)
    String studentKey;

    UUID systemKey;
}
