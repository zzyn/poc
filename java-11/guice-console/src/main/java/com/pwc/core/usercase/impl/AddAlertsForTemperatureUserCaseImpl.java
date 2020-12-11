package com.pwc.core.usercase.impl;

import com.google.inject.Inject;
import com.pwc.core.entity.TemperatureAlert;
import com.pwc.core.handler.AlertHandler;
import com.pwc.core.usercase.AddAlertsForTemperatureUserCase;
import com.pwc.dataproviders.dto.Temperature;
import com.pwc.dataproviders.dto.Threshold;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddAlertsForTemperatureUserCaseImpl implements AddAlertsForTemperatureUserCase {

    private final AlertHandler alertHandler;

    @Inject
    public AddAlertsForTemperatureUserCaseImpl(AlertHandler alertHandler) {
        Objects.requireNonNull(alertHandler,"alertHandler");
        this.alertHandler = alertHandler;
    }

    @Override
    public List<TemperatureAlert> execute(Threshold threshold, List<Temperature> temperatures) {
        Objects.requireNonNull(threshold, "threshold");
        Objects.requireNonNull(temperatures, "temperatures");

        List<TemperatureAlert> list = new ArrayList<>();
        boolean boilingTrigger = false;
        boolean freezingTrigger = false;

        for (Temperature temperature : temperatures) {
            List<String> alerts = new ArrayList<>();
            freezingTrigger = alertHandler.calcFreezeAlert(threshold, temperature, freezingTrigger, alerts);
            boilingTrigger = alertHandler.calcBoilAlert(threshold, temperature, boilingTrigger, alerts);
            list.add(new TemperatureAlert()
                    .setTemperature(temperature.getRaw())
                    .setAlerts(alerts));
        }
        return list;
    }
}
