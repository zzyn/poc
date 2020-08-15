package com.webflux.cassandra.demo.domain.model.businessenum;


import com.webflux.cassandra.demo.core.validator.businessenum.CustomEnum;

public enum ProductIdEnum implements CustomEnum {
    /*
     * Learning plan&result product id
     */
    SMALLSTAR(1),
    HIGHFLYER(2),
    TRAILBLAZERS(4),
    FRONTRUNNER (8),
    GRAMMARPRO(16),
    MOCKTEST(32),
    REMEDIATION(64);

    private int code;

    private ProductIdEnum(int code) {
        this.code = code;
    }

    @Override
    public int toCode() {
        return code;
    }
}
