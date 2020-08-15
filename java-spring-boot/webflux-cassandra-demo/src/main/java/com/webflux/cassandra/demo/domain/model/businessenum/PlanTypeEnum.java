package com.webflux.cassandra.demo.domain.model.businessenum;


import com.webflux.cassandra.demo.core.validator.businessenum.CustomEnum;

public enum PlanTypeEnum implements CustomEnum {
    /*
     * Learning plan&result  Plan Types
     */
    HOMEWORK(1),
    QUIZ(2),
    PRACTICE(4),
    DIAGNOSTICTEST(8),
    PROGRESSTEST(16),
    MOCKTEST(32);

    private int code;
    private PlanTypeEnum(int code) {
        this.code = code;
    }

    @Override
    public int toCode() {
        return code;
    }
}
