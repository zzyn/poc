package com.pwc.core.handler.impl;

import com.google.inject.Inject;
import com.pwc.core.entity.BoilTriggerDirection;
import com.pwc.core.entity.FreezeTriggerDirection;
import com.pwc.core.handler.AlertHandler;
import com.pwc.core.handler.BoilTriggerHandler;
import com.pwc.core.handler.FreezeTriggerHandler;
import com.pwc.dataproviders.dto.Temperature;
import com.pwc.dataproviders.dto.Threshold;

import java.util.List;
import java.util.Objects;

public class AlertHandlerImpl implements AlertHandler {

    private final BoilTriggerHandler boilTriggerHandler;
    private final FreezeTriggerHandler freezeTriggerHandler;

    @Inject
    public AlertHandlerImpl(BoilTriggerHandler boilTriggerHandler, FreezeTriggerHandler freezeTriggerHandler) {
        Objects.requireNonNull(boilTriggerHandler, "boilTriggerHandler");
        Objects.requireNonNull(freezeTriggerHandler, "freezeTriggerHandler");
        this.boilTriggerHandler = boilTriggerHandler;
        this.freezeTriggerHandler = freezeTriggerHandler;
    }

    @Override
    public boolean calcFreezeAlert(Threshold threshold, Temperature temperature, boolean isTrigger, List<String> alerts) {
        Objects.requireNonNull(threshold, "threshold");
        Objects.requireNonNull(temperature, "temperature");
        Objects.requireNonNull(alerts, "alerts");

        FreezeTriggerDirection freezeTriggerDirection = freezeTriggerHandler.execute(threshold, temperature);

        if (freezeTriggerDirection.equals(FreezeTriggerDirection.DOWN) && !isTrigger) {
            isTrigger = true;
            alerts.add(FreezeTriggerDirection.DOWN.getCode());
        }

        if (freezeTriggerDirection.equals(FreezeTriggerDirection.UP) && isTrigger) {
            isTrigger = false;
            alerts.add(FreezeTriggerDirection.UP.getCode());
        }

        return isTrigger;
    }

    @Override
    public boolean calcBoilAlert(Threshold threshold, Temperature temperature, boolean isTrigger, List<String> alerts) {
        Objects.requireNonNull(threshold, "threshold");
        Objects.requireNonNull(temperature, "temperature");
        Objects.requireNonNull(alerts, "alerts");

        BoilTriggerDirection boilTriggerDirection = boilTriggerHandler.execute(threshold, temperature);
        if (boilTriggerDirection.equals(BoilTriggerDirection.UP) && !isTrigger) {
            isTrigger = true;
            alerts.add(BoilTriggerDirection.UP.getCode());
        }

        if (boilTriggerDirection.equals(BoilTriggerDirection.DOWN) && isTrigger) {
            isTrigger = false;
            alerts.add(BoilTriggerDirection.DOWN.getCode());
        }
        return isTrigger;
    }
}
