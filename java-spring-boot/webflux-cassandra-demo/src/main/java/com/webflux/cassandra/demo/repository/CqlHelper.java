package com.webflux.cassandra.demo.repository;


import com.webflux.cassandra.demo.core.utility.PlanUtility;
import com.webflux.cassandra.demo.domain.model.PlanQueryModel;

import static com.webflux.cassandra.demo.domain.Constants.*;

/**
 * CqlHelper class
 *
 * @author ef edtech
 */
class CqlHelper {
    private CqlHelper(){}

    protected static String deleteCql(PlanQueryModel query){
        return PLAN_DELETE_CQL_STATEMENT + PLAN_TABLE_NAME + whereCql(query);
    }

    protected static String selectCql(PlanQueryModel query, int limit)
    {
        StringBuilder queryCql=new StringBuilder(PLAN_SELECT_CQL_STATEMENT).append(PLAN_TABLE_NAME).append(whereCql(query));

        if(PlanUtility.hasIntValue(limit)) {
            queryCql.append(LIMIT).append(limit);
        }

        return queryCql.toString();
    }

    private static String whereCql(PlanQueryModel query) {
        if (!PlanUtility.hasIntValue(query.getProductId()) || !PlanUtility.hasIntValue(query.getBucketId()) || !PlanUtility.hasStringValue(query.getPlanBusinessKey())) {
            return "";
        }
        StringBuilder cql = new StringBuilder(WHERE)
                .append(PRODUCT_ID).append(EQ).append(query.getProductId())
                .append(AND).append(PLAN_BUSINESS_KEY).append(EQ).append(LD).append(query.getPlanBusinessKey()).append(LD)
                .append(AND).append(BUCKET_ID).append(EQ).append(query.getBucketId());
        if (!PlanUtility.hasStringValue(query.getStudentKey())) {
            return cql.toString();
        }
        cql.append(AND).append(STUDENT_KEY).append(EQ).append(LD).append(query.getStudentKey()).append(LD);
        if (!PlanUtility.hasUuidValue(query.getSystemKey())) {
            return cql.toString();
        }
        cql.append(AND).append(SYSTEM_KEY).append(EQ).append(query.getSystemKey());
        return cql.toString();
    }
}
