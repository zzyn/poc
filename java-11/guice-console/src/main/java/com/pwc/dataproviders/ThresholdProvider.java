package com.pwc.dataproviders;

import com.pwc.dataproviders.dto.Threshold;

import java.util.Objects;

public interface ThresholdProvider {

    Threshold getThreshold();

    default Threshold getThreshold(String freezing, String boiling, String fluctuation){
        Objects.requireNonNull(freezing,"freezing");
        Objects.requireNonNull(boiling,"boiling");
        Objects.requireNonNull(fluctuation, "fluctuation");
        return new Threshold()
                .setFluctuation(Float.valueOf(fluctuation.trim()))
                .setFreezing(Float.valueOf(freezing.trim()))
                .setBoiling(Float.valueOf(boiling.trim()));
    }
}
