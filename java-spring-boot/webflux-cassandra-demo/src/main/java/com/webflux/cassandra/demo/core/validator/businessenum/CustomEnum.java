package com.webflux.cassandra.demo.core.validator.businessenum;

import com.fasterxml.jackson.annotation.JsonValue;

public interface CustomEnum {

    @JsonValue
    int toCode();
}
