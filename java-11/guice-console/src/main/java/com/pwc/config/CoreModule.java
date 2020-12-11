package com.pwc.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.pwc.core.handler.OutputHandler;
import com.pwc.core.handler.impl.OutputHandlerImpl;
import com.pwc.core.usercase.AddAlertsForTemperatureUserCase;
import com.pwc.core.usercase.impl.AddAlertsForTemperatureUserCaseImpl;

public class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        this.install(new AlertModule());
        this.bind(AddAlertsForTemperatureUserCase.class).to(AddAlertsForTemperatureUserCaseImpl.class).in(Scopes.SINGLETON);
        this.bind(OutputHandler.class).to(OutputHandlerImpl.class).in(Scopes.SINGLETON);
    }
}
