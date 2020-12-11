package com.pwc.app;

import com.google.inject.Inject;
import com.pwc.core.entity.TemperatureAlert;
import com.pwc.core.handler.OutputHandler;
import com.pwc.core.usercase.AddAlertsForTemperatureUserCase;
import com.pwc.dataproviders.TemperatureProvider;
import com.pwc.dataproviders.ThresholdProvider;
import com.pwc.dataproviders.dto.Temperature;
import com.pwc.dataproviders.dto.Threshold;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@Slf4j
public class App {

    private final AddAlertsForTemperatureUserCase addAlertsForTemperatureUserCase;
    private final ThresholdProvider thresholdProvider;
    private final TemperatureProvider temperatureProvider;
    private final OutputHandler outputHandler;

    @Inject
    public App(AddAlertsForTemperatureUserCase addAlertsForTemperatureUserCase, ThresholdProvider thresholdProvider, TemperatureProvider temperatureProvider, OutputHandler outputHandler) {
        Objects.requireNonNull(addAlertsForTemperatureUserCase, "addAlertsForTemperatureUserCase");
        Objects.requireNonNull(thresholdProvider, "thresholdProvider");
        Objects.requireNonNull(temperatureProvider, "temperatureProvider");
        Objects.requireNonNull(outputHandler, "outputHandler");
        this.addAlertsForTemperatureUserCase = addAlertsForTemperatureUserCase;
        this.thresholdProvider = thresholdProvider;
        this.temperatureProvider = temperatureProvider;
        this.outputHandler = outputHandler;
    }

    public String execute() {
        log.info("1. set up threshold.");
        Threshold threshold = thresholdProvider.getThreshold();
        log.info("2. set up temperatures.");
        List<Temperature> list = temperatureProvider.getTemperatures();
        log.info("3. trigger alert.");
        List<TemperatureAlert> alerts = addAlertsForTemperatureUserCase.execute(threshold, list);
        log.info("4. format output.");
        return outputHandler.convert(alerts);
    }
}
