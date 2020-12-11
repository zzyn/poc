package com.pwc.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.pwc.dataproviders.TemperatureProvider;
import com.pwc.dataproviders.ThresholdProvider;
import com.pwc.dataproviders.impl.TemperatureProviderConsoleImpl;
import com.pwc.dataproviders.impl.ThresholdProviderConsoleImpl;

public class ProviderConsoleModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(ThresholdProvider.class).to(ThresholdProviderConsoleImpl.class).in(Scopes.SINGLETON);
        this.bind(TemperatureProvider.class).to(TemperatureProviderConsoleImpl.class).in(Scopes.SINGLETON);
    }
}
