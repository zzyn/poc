package com.pwc.mock.bind;

import com.google.inject.AbstractModule;
import com.pwc.config.CoreModule;
import com.pwc.dataproviders.TemperatureProvider;
import com.pwc.dataproviders.ThresholdProvider;

public class MockAppModule extends AbstractModule {

    private final ThresholdProvider thresholdProvider;
    private final TemperatureProvider temperatureProvider;

    public MockAppModule(ThresholdProvider thresholdProvider, TemperatureProvider temperatureProvider){
        this.thresholdProvider = thresholdProvider;
        this.temperatureProvider = temperatureProvider;
    }

    @Override
    protected void configure() {
        this.install(new MockProviderModule(thresholdProvider, temperatureProvider));
        this.install(new CoreModule());
    }
}
