package com.webflux.cassandra.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.webflux.cassandra.demo.core.validator.PlanKey;
import com.webflux.cassandra.demo.core.validator.businessenum.BusinessEnumMessage;
import com.webflux.cassandra.demo.domain.model.businessenum.PlanTypeEnum;
import com.webflux.cassandra.demo.domain.model.businessenum.ProductIdEnum;
import com.webflux.cassandra.demo.domain.model.businessenum.StateEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

import static com.webflux.cassandra.demo.domain.Constants.*;


/**
 * PlanRequestModel class
 *
 * @author ef edtech
 */
@JsonAutoDetect
@Getter
@Setter
public class PlanRequestModel{

    @BusinessEnumMessage(enumClass = ProductIdEnum.class, message = PRODUCT_ID_ERROR_CODE)
    private int productId;

    @PlanKey(min = PLAN_BUSINESS_KEY_MIN_LENGTH, max = PLAN_BUSINESS_KEY_MAX_LENGTH, message = PLAN_BUSINESS_KEY_LENGTH_ERROR_CODE,allowEmpty =false, allowNull = false, applyPattern = true)
    private String planBusinessKey;

    @Min(value = BUCKET_ID_DEFAULT_VALUE,message = BUCKET_ID_MIN_ERROR_CODE)
    @Max(value = BUCKET_ID_MAX_LENGTH,message = BUCKET_ID_MAX_ERROR_CODE)
    private int bucketId;

    @PlanKey( message = STUDENT_KEY_LENGTH_ERROR_CODE, max = STUDENT_KEY_MAX_LENGTH, min = STUDENT_KEY_MIN_LENGTH,allowNull = false, allowEmpty = false, applyPattern = true)
    private String studentKey;

    @BusinessEnumMessage(enumClass = PlanTypeEnum.class, message = PLAN_TYPE_ERROR_CODE)
    private int planType;

    @BusinessEnumMessage(enumClass = StateEnum.class, message = STATE_ERROR_CODE)
    private int state;

    @PlanKey( message = LEARNING_UNIT_LENGTH_ERROR_CODE, max = LEARNING_UNIT_MAX_LENGTH, min = LEARNING_UNIT_MIN_LENGTH, allowEmpty = true,allowNull = true, applyPattern = false)
    private String learningUnit;

    @PlanKey( message = ROUTE_LENGTH_ERROR_CODE, max = ROUTE_MAX_LENGTH, min = ROUTE_MIN_LENGTH, allowEmpty = true,allowNull = true, applyPattern = false)
    private String route;

    @JsonFormat(timezone = "UTC", pattern = LOCAL_DATETIME_PATTERN)
    @DateTimeFormat(pattern = LOCAL_DATETIME_PATTERN)
    private Date startTime;

    @JsonFormat(timezone = "UTC", pattern = LOCAL_DATETIME_PATTERN)
    @DateTimeFormat(pattern = LOCAL_DATETIME_PATTERN)
    private Date endTime;

    @JsonFormat(timezone = "UTC", pattern = LOCAL_DATETIME_PATTERN)
    @DateTimeFormat(pattern = LOCAL_DATETIME_PATTERN)
    private Date createdTime;

    @PlanKey( message = CREATED_BY_LENGTH_ERROR_CODE, max = CREATED_BY_MAX_LENGTH, min = CREATED_BY_MIN_LENGTH, allowEmpty = true,allowNull = true, applyPattern = false)
    private String createdBy;

    @JsonFormat(timezone = "UTC", pattern = LOCAL_DATETIME_PATTERN)
    @DateTimeFormat(pattern = LOCAL_DATETIME_PATTERN)
    private Date lastUpdatedTime;

    @PlanKey( message = LAST_UPDATED_BY_LENGTH_ERROR_CODE, max = LAST_UPDATED_BY_MAX_LENGTH, min = LAST_UPDATED_BY_MIN_LENGTH, allowEmpty = true,allowNull = true, applyPattern = false)
    private String lastUpdatedBy;
}
