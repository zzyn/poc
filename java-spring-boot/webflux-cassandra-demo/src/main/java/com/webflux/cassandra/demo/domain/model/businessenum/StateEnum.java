package com.webflux.cassandra.demo.domain.model.businessenum;

import com.webflux.cassandra.demo.core.validator.businessenum.CustomEnum;

public enum StateEnum implements CustomEnum {
    /*
     * Learning plan&result state Types
     */
    INIT(1),
    INPROGRESS(2),
    COMPLETED(4),
    CANCELED(8);
    private int code;

    private StateEnum(int code) {
        this.code = code;
    }

    @Override
    public int toCode() {
        return code;
    }
}
