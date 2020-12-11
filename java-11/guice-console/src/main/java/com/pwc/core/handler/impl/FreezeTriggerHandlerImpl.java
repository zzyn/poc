package com.pwc.core.handler.impl;

import com.pwc.core.entity.FreezeTriggerDirection;
import com.pwc.core.handler.FreezeTriggerHandler;
import com.pwc.dataproviders.dto.Temperature;
import com.pwc.dataproviders.dto.Threshold;

import java.util.Objects;

public class FreezeTriggerHandlerImpl implements FreezeTriggerHandler {

    @Override
    public FreezeTriggerDirection execute(Threshold threshold, Temperature temperature) {

        Objects.requireNonNull(threshold, "threshold");
        Objects.requireNonNull(temperature, "temperature");

        if(temperature.getVal() <= threshold.getFreezing()) {
            return FreezeTriggerDirection.DOWN;
        }

        if(temperature.getVal() > (threshold.getFreezing() + threshold.getFluctuation())) {
            return FreezeTriggerDirection.UP;
        }

        return FreezeTriggerDirection.NONE;
    }
}
