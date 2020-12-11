package com.pwc.cases.unit;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.pwc.config.AlertModule;
import com.pwc.core.handler.AlertHandler;
import com.pwc.dataproviders.dto.Temperature;
import com.pwc.dataproviders.dto.Threshold;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AlertHandlerUnitTest {

    private static AlertHandler alertHandler;

    @BeforeAll
    public static void init() {
        Injector injector = Guice.createInjector(new AlertModule());
        alertHandler = injector.getInstance(AlertHandler.class);
    }

    @DisplayName("calcBoilAlert_threshold_is_null_threw_exception")
    @Tag("exception")
    @Test
    public void calcBoilAlert_threshold_is_null_threw_exception() {
        assertThrows(NullPointerException.class, () -> {
            alertHandler.calcBoilAlert(null, new Temperature(), false, new ArrayList<>());
        });
    }

    @DisplayName("calcBoilAlert_temperature_is_null_threw_exception")
    @Tag("exception")
    @Test
    public void calcBoilAlert_temperature_is_null_threw_exception() {
        assertThrows(NullPointerException.class, () -> {
            alertHandler.calcBoilAlert(new Threshold(), null, false, new ArrayList<>());
        });
    }

    @DisplayName("calcBoilAlert_alerts_is_null_threw_exception")
    @Tag("exception")
    @Test
    public void calcBoilAlert_alerts_is_null_threw_exception() {
        assertThrows(NullPointerException.class, () -> {
            alertHandler.calcBoilAlert(new Threshold(), new Temperature(), false, null);
        });
    }


    @DisplayName("calcFreezeAlert_threshold_is_null_threw_exception")
    @Tag("exception")
    @Test
    public void calcFreezeAlert_threshold_is_null_threw_exception() {
        assertThrows(NullPointerException.class, () -> {
            alertHandler.calcFreezeAlert(null, new Temperature(), false, new ArrayList<>());
        });
    }

    @DisplayName("calcFreezeAlert_temperature_is_null_threw_exception")
    @Tag("exception")
    @Test
    public void calcFreezeAlert_temperature_is_null_threw_exception() {
        assertThrows(NullPointerException.class, () -> {
            alertHandler.calcFreezeAlert(new Threshold(), null, false, new ArrayList<>());
        });
    }

    @DisplayName("calcFreezeAlert_alerts_is_null_threw_exception")
    @Tag("exception")
    @Test
    public void calcFreezeAlert_alerts_is_null_threw_exception() {
        assertThrows(NullPointerException.class, () -> {
            alertHandler.calcFreezeAlert(new Threshold(), new Temperature(), false, null);
        });
    }

}
