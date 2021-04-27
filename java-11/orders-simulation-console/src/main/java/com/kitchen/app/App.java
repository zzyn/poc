package com.kitchen.app;

import com.google.inject.Inject;
import com.kitchen.core.handler.IngestOrderHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class App {

    private final IngestOrderHandler ingestOrderHandler;

    @Inject
    public App(IngestOrderHandler ingestOrderHandler) {
        Objects.requireNonNull(ingestOrderHandler, "ingestOrderHandler is required");
        this.ingestOrderHandler = ingestOrderHandler;
    }

    public void execute() {
        log.info("scheduler is working");
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(ingestOrderHandler, 5, 1, TimeUnit.SECONDS);
    }
}
