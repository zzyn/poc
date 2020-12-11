package com.pwc.cases.unit;

import com.pwc.core.entity.BoilTriggerDirection;
import com.pwc.core.handler.BoilTriggerHandler;
import com.pwc.core.handler.impl.BoilTriggerHandlerImpl;
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
public class BoilTriggerHandlerUnitTest {

    private static BoilTriggerHandler boilTriggerHandler;

    @BeforeAll
    public static void init() {
        boilTriggerHandler = new BoilTriggerHandlerImpl();
    }

    @DisplayName("threshold_is_null_threw_exception")
    @Tag("exception")
    @Test
    public void threshold_is_null_threw_exception() {
        assertThrows(NullPointerException.class, () -> {
            boilTriggerHandler.execute(null, new Temperature());
        });
    }

    @DisplayName("temperature_is_null_threw_exception")
    @Tag("exception")
    @Test
    public void temperature_is_null_threw_exception() {
        assertThrows(NullPointerException.class, () -> {
            boilTriggerHandler.execute(MockThreshold.getThreshold(), null);
        });
    }

    @DisplayName("temperature_is_boil_up")
    @Tag("normal")
    @Test
    public void temperature_is_boil_up() {
        assertEquals(BoilTriggerDirection.UP, boilTriggerHandler.execute(MockThreshold.getThreshold(), new Temperature().setRaw("100").setVal(100f)));
    }

    @DisplayName("temperature_is_boil_down")
    @Tag("normal")
    @Test
    public void temperature_is_boil_down() {
        assertEquals(BoilTriggerDirection.DOWN, boilTriggerHandler.execute(MockThreshold.getThreshold(), new Temperature().setRaw("95").setVal(95f)));
    }

    @DisplayName("temperature_is_boil_none")
    @Tag("normal")
    @Test
    public void temperature_is_boil_none() {
        assertEquals(BoilTriggerDirection.NONE, boilTriggerHandler.execute(MockThreshold.getThreshold(), new Temperature().setRaw("99.5").setVal(99.5f)));
    }
}
