package com.pwc.cases.unit;

import com.pwc.core.entity.TemperatureAlert;
import com.pwc.core.handler.OutputHandler;
import com.pwc.core.handler.impl.OutputHandlerImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OutputHandlerUnitTest {

    private static OutputHandler outputHandler;

    @BeforeAll
    public static void init() {
        outputHandler = new OutputHandlerImpl();
    }

    @DisplayName("alerts_is_null_return_empty")
    @Tag("exception")
    @Test
    public void alerts_is_null_return_empty() {
        Assertions.assertEquals("", outputHandler.convert(null));
    }

    @DisplayName("alerts_is_empty_return_empty")
    @Tag("exception")
    @Test
    public void alerts_is_empty_return_empty() {
        Assertions.assertEquals("", outputHandler.convert(new ArrayList<>()));
    }

    @DisplayName("alerts_is_single")
    @Tag("normal")
    @Test
    public void alerts_is_single() {
        List<TemperatureAlert> list = new ArrayList<>();
        list.add(new TemperatureAlert().setTemperature("50"));
        assertEquals("50", outputHandler.convert(list));
    }

    @DisplayName("alerts_are_more")
    @Tag("normal")
    @Test
    public void alerts_are_more() {
        List<TemperatureAlert> list = new ArrayList<>();
        list.add(new TemperatureAlert().setTemperature("0").setAlerts(Collections.singletonList("freezing")));
        list.add(new TemperatureAlert().setTemperature("0.1"));
        assertEquals("0 freezing 0.1", outputHandler.convert(list));
    }

}
