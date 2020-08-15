package com.webflux.cassandra.demo.domain;

public class Constants {

    private Constants(){}
    public static final String SYSTEM_KEY="systemkey";
    public static final String STUDENT_KEY="studentkey";
    public static final String PRODUCT_ID="productid";
    public static final String PLAN_BUSINESS_KEY="planbusinesskey";
    public static final String PLAN_TYPE="plantype";
    public static final String ROUTE="route";
    public static final String STATE="state";
    public static final String CREATED_TIME="createdtime";
    public static final String CREATED_BY="createdby";
    public static final String LAST_UPDATED_TIME="lastupdatedtime";
    public static final String LAST_UPDATED_BY="lastupdatedby";
    public static final String START_TIME="starttime";
    public static final String END_TIME="endtime";
    public static final String LEARNING_UNIT="learningunit";
    public static final String BUCKET_ID="bucketid";
    public static final String EQ="=";
    public static final String AND=" and ";
    public static final String LD="'";
    public static final String WHERE=" where ";
    public static final String LIMIT=" limit ";
    public static final String IFEXISTS=" if exists ";
    public static final String PLAN_TABLE_NAME=" studentplan ";
    public static final String PLAN_SELECT_CQL_STATEMENT="select * from  ";
    public static final String PLAN_DELETE_CQL_STATEMENT="delete from  ";
    public static final String STUDENT_KEY_LENGTH_ERROR_CODE="4401";
    public static final String PRODUCT_ID_ERROR_CODE="4402";
    public static final String PLAN_TYPE_ERROR_CODE="4405";
    public static final String PLAN_BUSINESS_KEY_LENGTH_ERROR_CODE="4404";
    public static final String ROUTE_LENGTH_ERROR_CODE="4407";
    public static final String STATE_ERROR_CODE="4408";
    public static final String BUCKET_ID_MIN_ERROR_CODE="4409";
    public static final String BUCKET_ID_MAX_ERROR_CODE="4418";
    public static final String CREATED_BY_LENGTH_ERROR_CODE="4414";
    public static final String LAST_UPDATED_BY_LENGTH_ERROR_CODE="4415";
    public static final String LEARNING_UNIT_LENGTH_ERROR_CODE="4416";
    public static final String SYSTEM_KEY_NULL_ERROR_CODE="4417";
    public static final String CASSANDRA_TIMESTAMP_FORMAT_REGEX="\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.*\\d{0,4}";
    public static final String LOCAL_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String KEY_VALIDATOR = "^[A-Za-z0-9|-]+$";
    public static final String TIMEUUID_FORMAT_REGEX="[\\w]{8}(-[\\w]{4}){3}-[\\w]{12}";
    public static final int PRODUCT_ID_MAX_LENGTH=512;
    public static final int PLAN_BUSINESS_KEY_MIN_LENGTH=1;
    public static final int PLAN_BUSINESS_KEY_MAX_LENGTH=512;
    public static final int STUDENT_KEY_MIN_LENGTH=1;
    public static final int STUDENT_KEY_MAX_LENGTH=128;
    public static final int BUCKET_ID_MAX_LENGTH=Integer.MAX_VALUE-1;
    public static final int ROUTE_MAX_LENGTH=512;
    public static final int ROUTE_MIN_LENGTH=0;
    public static final int CREATED_BY_MAX_LENGTH=128;
    public static final int CREATED_BY_MIN_LENGTH=0;
    public static final int LAST_UPDATED_BY_MIN_LENGTH=0;
    public static final int LAST_UPDATED_BY_MAX_LENGTH=128;
    public static final int BUCKET_ID_DEFAULT_VALUE=1;
    public static final int LEARNING_UNIT_MIN_LENGTH=0;
    public static final int LEARNING_UNIT_MAX_LENGTH=4096;
    public static final int PLAN_MAX_LIMIT=50;
    public static final int PLAN_MAX_PAGE=1;
}
