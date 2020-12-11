package com.pwc.core.handler.impl;

import com.pwc.core.entity.BoilTriggerDirection;
import com.pwc.core.handler.BoilTriggerHandler;
import com.pwc.dataproviders.dto.Temperature;
import com.pwc.dataproviders.dto.Threshold;

import java.util.Objects;

public class BoilTriggerHandlerImpl implements BoilTriggerHandler {

    @Override
    public BoilTriggerDirection execute(Threshold threshold, Temperature temperature) {

        Objects.requireNonNull(threshold, "threshold");
        Objects.requireNonNull(temperature, "temperature");

        if(temperature.getVal() >= threshold.getBoiling()) {
            return BoilTriggerDirection.UP;
        }

        if(temperature.getVal() < (threshold.getBoiling() - threshold.getFluctuation())) {
            return BoilTriggerDirection.DOWN;
        }
        return BoilTriggerDirection.NONE;
    }
}
