package com.pwc.cases.unit;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.pwc.config.CoreModule;
import com.pwc.core.usercase.AddAlertsForTemperatureUserCase;
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
public class AddAlertsForTemperatureUserCaseUnitTest {

    private static AddAlertsForTemperatureUserCase addAlertsForTemperatureUserCase;

    @BeforeAll
    public static void init() {
        Injector injector = Guice.createInjector(new CoreModule());
        addAlertsForTemperatureUserCase = injector.getInstance(AddAlertsForTemperatureUserCase.class);
    }

    @DisplayName("threshold_is_null_threw_exception")
    @Tag("exception")
    @Test
    public void threshold_is_null_threw_exception() {
        assertThrows(NullPointerException.class, () -> {
            addAlertsForTemperatureUserCase.execute(null, new ArrayList<>());
        });
    }

    @DisplayName("temperatures_is_null_threw_exception")
    @Tag("exception")
    @Test
    public void temperatures_is_null_threw_exception() {
        assertThrows(NullPointerException.class, () -> {
            addAlertsForTemperatureUserCase.execute(new Threshold(), null);
        });
    }
}
