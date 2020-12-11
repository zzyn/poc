package com.pwc.config;

import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

    private final boolean isDebug;

    public AppModule(boolean isDebug) {
        this.isDebug = isDebug;
    }

    @Override
    protected void configure() {
        if (isDebug) {
            this.install(new ProviderDebugModule());
        } else {
            this.install(new ProviderConsoleModule());
        }
        this.install(new CoreModule());
    }
}
