package com.pwc.mock.bind;

import com.google.inject.AbstractModule;
import com.pwc.dataproviders.TemperatureProvider;
import com.pwc.dataproviders.ThresholdProvider;

public class MockProviderModule extends AbstractModule {

    private final ThresholdProvider thresholdProvider;
    private final TemperatureProvider temperatureProvider;

    public MockProviderModule(ThresholdProvider thresholdProvider, TemperatureProvider temperatureProvider){
        this.thresholdProvider = thresholdProvider;
        this.temperatureProvider = temperatureProvider;
    }

    @Override
    protected void configure() {
        this.bind(ThresholdProvider.class).toInstance(thresholdProvider);
        this.bind(TemperatureProvider.class).toInstance(temperatureProvider);
    }
}
