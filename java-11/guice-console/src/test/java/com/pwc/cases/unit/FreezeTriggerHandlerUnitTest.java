package com.pwc.cases.unit;

import com.pwc.core.entity.FreezeTriggerDirection;
import com.pwc.core.handler.FreezeTriggerHandler;
import com.pwc.core.handler.impl.FreezeTriggerHandlerImpl;
import com.pwc.dataproviders.dto.Temperature;
import com.pwc.mock.data.MockThreshold;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class FreezeTriggerHandlerUnitTest {

    private static FreezeTriggerHandler freezeTriggerHandler;

    @BeforeAll
    public static void init() {
        freezeTriggerHandler = new FreezeTriggerHandlerImpl();
    }

    @DisplayName("threshold_is_null_threw_exception")
    @Tag("exception")
    @Test
    public void threshold_is_null_threw_exception() {
        assertThrows(NullPointerException.class, () -> {
            freezeTriggerHandler.execute(null, new Temperature());
        });
    }

    @DisplayName("temperature_is_null_threw_exception")
    @Tag("exception")
    @Test
    public void temperature_is_null_threw_exception() {
        assertThrows(NullPointerException.class, () -> {
            freezeTriggerHandler.execute(MockThreshold.getThreshold(), null);
        });
    }

    @DisplayName("temperature_is_freeze_up")
    @Tag("normal")
    @Test
    public void temperature_is_freeze_up() {
        assertEquals(FreezeTriggerDirection.UP, freezeTriggerHandler.execute(MockThreshold.getThreshold(), new Temperature().setRaw("10").setVal(10f)));
    }

    @DisplayName("temperature_is_freeze_down")
    @Tag("normal")
    @Test
    public void temperature_is_freeze_down() {
        assertEquals(FreezeTriggerDirection.DOWN, freezeTriggerHandler.execute(MockThreshold.getThreshold(), new Temperature().setRaw("0").setVal(0f)));
    }

    @DisplayName("temperature_is_freeze_none")
    @Tag("normal")
    @Test
    public void temperature_is_freeze_none() {
        assertEquals(FreezeTriggerDirection.NONE, freezeTriggerHandler.execute(MockThreshold.getThreshold(), new Temperature().setRaw("0.5").setVal(0.5f)));
    }
}
