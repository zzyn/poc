package com.webflux.cassandra.demo.core.entity;

import com.datastax.driver.core.ConsistencyLevel;

import java.io.Serializable;

public class ConsistencyContext implements Serializable {

    private ConsistencyLevel consistencyLevel = ConsistencyLevel.QUORUM;

    public ConsistencyLevel getConsistencyLevel() {
        return consistencyLevel;
    }

    public void setConsistencyLevel(ConsistencyLevel consistencyLevel) {
        this.consistencyLevel = consistencyLevel;
    }
}
