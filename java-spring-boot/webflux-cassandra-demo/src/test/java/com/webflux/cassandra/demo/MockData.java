package com.webflux.cassandra.demo;

import com.datastax.driver.core.utils.UUIDs;
import com.webflux.cassandra.demo.core.mapper.PlanMapperUtility;
import com.webflux.cassandra.demo.core.utility.PlanUtility;
import com.webflux.cassandra.demo.domain.model.PlanCollectionRequestModel;
import com.webflux.cassandra.demo.domain.model.PlanQueryModel;
import com.webflux.cassandra.demo.domain.model.PlanRequestModel;
import com.webflux.cassandra.demo.domain.model.PlanUpdateRequestModel;
import com.webflux.cassandra.demo.domain.model.businessenum.PlanTypeEnum;
import com.webflux.cassandra.demo.domain.model.businessenum.ProductIdEnum;
import com.webflux.cassandra.demo.domain.model.businessenum.StateEnum;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MockData {
    public static final String CREATED_BY = "integration-test-admin|test";
    public static String CREATED_BY_UPDATE_AFTER = "integration-test admin|test-after";
    public static String LAST_UPDATED_BY = "integration-test-admin-test";
    public static String LAST_UPDATED_BY_UPDATE_AFTER = "integration-test|admin-test-after";
    public static String LEARNING_UNIT = "<footer><pstyle=\"text-align:center;\"><ahref=\"/support/license.html\">License</a>|<ahref=\"/support/privacy.html\">PrivacyPolicy</a>|<ahref=\"/support/terms.html\">TermsofService</a></p></footer>";
    public static String LEARNING_UNIT_UPDATE_AFTER = "integration-test-learning-unit-test after";
    public static String PLAN_BUSINESS_KEY = "integration-test-plan|business|key-|test";
    public static String ROUTE = "integration-test route test";
    public static String ROUTE_UPDATE_AFTER = "integration-test|route-test|after";
    public static String STUDENT_KEY = "integration-test-testuser-1234-test";
    public static String STUDENT_KEY_2 = "integration-test-testuser-1234-test2";
    public static String student_key_spefical_characters = "integration-test-testuser|1234";
    public static int PLAN_TYPE = PlanTypeEnum.MOCKTEST.toCode();
    public static String SLASH_CHARACTER ="/";
    public static int PLAN_STATE_UPDATE_AFTER = 2;
    public static int PRODUCT_ID = ProductIdEnum.SMALLSTAR.toCode();
    public static String PLAN_ROOT_PATH="/plans/";
    public static String SPEFICAL_CHARACTERS="%$#^@~";
    public static String PLAN_INSERT_BATCH_PATH="/plans/batch";
    public static int BUCKET_ID_ERROR=14;
    public static int SAME_CLUSTER_KEY_IN_COLLECTION_COUNT=3;
    public static int TOTAL_IN_COLLECTION=4;

    public static PlanQueryModel getDefaultPlanQuery() throws Exception {
        return new PlanQueryModel(PRODUCT_ID,PLAN_BUSINESS_KEY,getDefaultBucketId(),STUDENT_KEY,UUIDs.timeBased());
    }
    public static PlanQueryModel getDefaultPlanQueryWithNullCluster() throws Exception {
        return new PlanQueryModel(PRODUCT_ID,PLAN_BUSINESS_KEY,getDefaultBucketId(),null,null);
    }
    public static int getDefaultBucketId()
    {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        return cal.get(Calendar.YEAR);
    }
    public static PlanUpdateRequestModel getPlanUpdateRequest() throws Exception {
        PlanRequestModel request=getPlanRequest();
        PlanUpdateRequestModel result= PlanMapperUtility.PLAN_UPDATE_REQUEST_MAPPER.planRequestToPlanUpdateRequest(request);
        result.setSystemKey(UUIDs.timeBased());
        return result;
    }
    public static Date getCurrent() {
        ZoneId zoneId = ZoneId.of(ZoneOffset.UTC.getId());
        LocalDate localDate = LocalDate.now();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }
    public static PlanRequestModel getPlanRequest() {
        PlanRequestModel plan =new PlanRequestModel();
        plan.setBucketId(getDefaultBucketId());
        plan.setCreatedBy(CREATED_BY);
        plan.setCreatedTime(getCurrent());
        plan.setLastUpdatedBy(LAST_UPDATED_BY);
        plan.setLastUpdatedTime(getCurrent());
        plan.setStartTime(getCurrent());
        plan.setEndTime(getCurrent());
        plan.setLearningUnit(LEARNING_UNIT);
        plan.setPlanBusinessKey(PLAN_BUSINESS_KEY);
        plan.setPlanType(PLAN_TYPE);
        plan.setProductId(PRODUCT_ID);
        plan.setRoute(ROUTE);
        plan.setStudentKey(STUDENT_KEY);
        plan.setState(StateEnum.COMPLETED.toCode());
        return plan;
    }
    public static PlanRequestModel getNullPlanRequest() {
        PlanRequestModel plan =new PlanRequestModel();
        plan.setBucketId(getDefaultBucketId());
        plan.setCreatedBy(null);
        plan.setCreatedTime(null);
        plan.setLastUpdatedBy(null);
        plan.setLastUpdatedTime(null);
        plan.setStartTime(null);
        plan.setEndTime(null);
        plan.setLearningUnit(null);
        plan.setPlanBusinessKey(PLAN_BUSINESS_KEY);
        plan.setPlanType(PLAN_TYPE);
        plan.setProductId(PRODUCT_ID);
        plan.setRoute(null);
        plan.setStudentKey(STUDENT_KEY);
        plan.setState(StateEnum.COMPLETED.toCode());
        return plan;
    }
    public static PlanRequestModel getNullAndEmptyPlanRequest() {
        PlanRequestModel plan =new PlanRequestModel();
        plan.setBucketId(getDefaultBucketId());
        plan.setCreatedBy("");
        plan.setCreatedTime(null);
        plan.setLastUpdatedBy("");
        plan.setLastUpdatedTime(null);
        plan.setStartTime(null);
        plan.setEndTime(null);
        plan.setLearningUnit("");
        plan.setPlanBusinessKey(PLAN_BUSINESS_KEY);
        plan.setPlanType(PLAN_TYPE);
        plan.setProductId(PRODUCT_ID);
        plan.setRoute("");
        plan.setStudentKey(STUDENT_KEY);
        plan.setState(StateEnum.COMPLETED.toCode());
        return plan;
    }

    public static PlanCollectionRequestModel getPlanRequestCollection() {
        PlanCollectionRequestModel result = new PlanCollectionRequestModel();
        List<PlanRequestModel> plans = new ArrayList<>();
        for(int i=0;i<SAME_CLUSTER_KEY_IN_COLLECTION_COUNT;i++)
        {
            PlanRequestModel requestModel = getPlanRequest();
            requestModel.setStudentKey(STUDENT_KEY_2);
            plans.add(requestModel);
        }
        for(int j=0;j<TOTAL_IN_COLLECTION-SAME_CLUSTER_KEY_IN_COLLECTION_COUNT;j++)
        {
            PlanRequestModel requestModel = getPlanRequest();
            plans.add(requestModel);
        }
        result.setRequests(plans);
        return result;
    }
    public static String getQueryPath(PlanQueryModel query) {
        StringBuilder result = new StringBuilder();
        if (!PlanUtility.hasIntValue(query.getProductId()) || !PlanUtility.hasIntValue(query.getBucketId()) || !PlanUtility.hasStringValue(query.getPlanBusinessKey()))
            return "";
        result.append(PLAN_ROOT_PATH).append(query.getProductId()).append("/").append(query.getBucketId()).append("/").append(query.getPlanBusinessKey());
        if (!PlanUtility.hasStringValue(query.getStudentKey())) {
            return result.toString();
        }
        result.append("?studentkey=").append(query.getStudentKey());

        if (query.getSystemKey()== null)
            return result.toString();
        result.append("&systemkey=").append(query.getSystemKey());
        return result.toString();
    }
    public static String getQueryPathWithPage(PlanQueryModel query,Integer limit,Integer pageIndex) {
        String queryPath = getQueryPath(query);
        if (limit != null) {
            queryPath = queryPath + getUrlChar(queryPath) + "limit=" + limit;
        }
        if (pageIndex != null) {
            queryPath = queryPath + getUrlChar(queryPath) + "page=" + pageIndex;
        }
        return queryPath;
    }
    private static String getUrlChar(String originalString) {
        if (originalString.contains("?"))
            return "&";
        return "?";
    }
    public static String getDeletePath(PlanQueryModel query) throws Exception {
        StringBuilder result = new StringBuilder();
        if (!PlanUtility.hasIntValue(query.getProductId()) || !PlanUtility.hasIntValue(query.getBucketId()) || !PlanUtility.hasStringValue(query.getPlanBusinessKey()))
            return "";
        result.append(PLAN_ROOT_PATH)
                .append(query.getProductId())
                .append(SLASH_CHARACTER)
                .append(query.getBucketId())
                .append(SLASH_CHARACTER)
                .append(query.getPlanBusinessKey());
        if (!PlanUtility.hasStringValue(query.getStudentKey())) {
            return result.toString();
        }
        result.append(SLASH_CHARACTER).append(query.getStudentKey());
        if (query.getSystemKey() != null)
            return result.toString();
        result.append(SLASH_CHARACTER).append(query.getSystemKey());
        return result.toString();
    }
}
