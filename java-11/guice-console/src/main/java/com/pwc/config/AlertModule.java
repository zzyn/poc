package com.pwc.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.pwc.core.handler.AlertHandler;
import com.pwc.core.handler.BoilTriggerHandler;
import com.pwc.core.handler.FreezeTriggerHandler;
import com.pwc.core.handler.impl.AlertHandlerImpl;
import com.pwc.core.handler.impl.BoilTriggerHandlerImpl;
import com.pwc.core.handler.impl.FreezeTriggerHandlerImpl;

public class AlertModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(FreezeTriggerHandler.class).to(FreezeTriggerHandlerImpl.class).in(Scopes.SINGLETON);
        this.bind(BoilTriggerHandler.class).to(BoilTriggerHandlerImpl.class).in(Scopes.SINGLETON);
        this.bind(AlertHandler.class).to(AlertHandlerImpl.class).in(Scopes.SINGLETON);
    }
}
