package com.webflux.cassandra.demo.repository;

import com.datastax.driver.core.Row;
import com.webflux.cassandra.demo.domain.dto.PlanDto;
import org.springframework.data.cassandra.core.cql.RowMapper;

import static com.webflux.cassandra.demo.domain.Constants.*;


enum PlanMapper implements RowMapper<PlanDto> {

    INSTANCE;

    @Override
    public PlanDto mapRow(Row row, int rowNum) {
        PlanDto plan = new PlanDto();
        plan.setSystemKey(row.getUUID(SYSTEM_KEY));
        plan.setStudentKey(row.getString(STUDENT_KEY));
        plan.setProductId(row.getInt(PRODUCT_ID));
        plan.setPlanBusinessKey(row.getString(PLAN_BUSINESS_KEY));
        plan.setPlanType(row.getInt(PLAN_TYPE));
        plan.setRoute(row.getString(ROUTE));
        plan.setState(row.getInt(STATE));
        plan.setCreatedTime(row.getTimestamp(CREATED_TIME));
        plan.setLastUpdatedTime(row.getTimestamp(LAST_UPDATED_TIME));
        plan.setStartTime(row.getTimestamp(START_TIME));
        plan.setEndTime(row.getTimestamp(END_TIME));
        plan.setCreatedBy(row.getString(CREATED_BY));
        plan.setLastUpdatedBy(row.getString(LAST_UPDATED_BY));
        plan.setBucketId(row.getInt(BUCKET_ID));
        plan.setLearningUnit(row.getString(LEARNING_UNIT));
        return plan;
    }
}
