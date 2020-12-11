package com.pwc.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.pwc.dataproviders.TemperatureProvider;
import com.pwc.dataproviders.ThresholdProvider;
import com.pwc.dataproviders.impl.TemperatureProviderDebugImpl;
import com.pwc.dataproviders.impl.ThresholdProviderDebugImpl;

public class ProviderDebugModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(ThresholdProvider.class).to(ThresholdProviderDebugImpl.class).in(Scopes.SINGLETON);
        this.bind(TemperatureProvider.class).to(TemperatureProviderDebugImpl.class).in(Scopes.SINGLETON);
    }
}
