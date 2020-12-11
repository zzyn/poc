package com.pwc.cases.component;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.pwc.app.App;
import com.pwc.dataproviders.TemperatureProvider;
import com.pwc.dataproviders.ThresholdProvider;
import com.pwc.mock.bind.MockAppModule;
import com.pwc.mock.data.MockTemperatures;
import com.pwc.mock.data.MockThreshold;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class AppComponentTest {

    private App app;
    private Injector injector;

    @Mock
    private ThresholdProvider thresholdProvider;

    @Mock
    private TemperatureProvider temperatureProvider;

    @BeforeEach
    public void setUp() {
        injector = Guice.createInjector(new MockAppModule(thresholdProvider, temperatureProvider));
        app = injector.getInstance(App.class);
        when(thresholdProvider.getThreshold()).thenReturn(MockThreshold.getThreshold());
    }

    @AfterEach
    public void tearDown() {

    }

    @DisplayName("sample 1: 4.0 1.0 0.5 0.0 -0.5 0.0 0.5 0.0 -2.0 0.0 0.5 0.6 2.0")
    @Tag("sample")
    @Test
    public void sample_1() {
        doReturn(MockTemperatures.getList("4.0 1.0 0.5 0.0 -0.5 0.0 0.5 0.0 -2.0 0.0 0.5 0.6 2.0"))
                .when(temperatureProvider)
                .getTemperatures();

        assertEquals("4.0 1.0 0.5 0.0 freezing -0.5 0.0 0.5 0.0 -2.0 0.0 0.5 0.6 unfreezing 2.0", app.execute());
    }

    @DisplayName("sample 2: 5.0 -0.5 0.5 -0.2 100 101")
    @Tag("sample")
    @Test
    public void sample_2() {
        doReturn(MockTemperatures.getList("5.0 -0.5 0.5 -0.2 100 101"))
                .when(temperatureProvider)
                .getTemperatures();

        assertEquals("5.0 -0.5 freezing 0.5 -0.2 100 unfreezing boiling 101", app.execute());
    }

    @DisplayName("sample 3: 0.0 0.3 0.5 0.4 0.7")
    @Tag("sample")
    @Test
    public void sample_3() {
        doReturn(MockTemperatures.getList("0.0 0.3 0.5 0.4 0.7"))
                .when(temperatureProvider)
                .getTemperatures();

        assertEquals("0.0 freezing 0.3 0.5 0.4 0.7 unfreezing", app.execute());
    }

    @DisplayName("sample 4: 99 100 99.5 99 10")
    @Tag("addition")
    @Test
    public void sample_4() {
        doReturn(MockTemperatures.getList("99 100 99.5 99 10"))
                .when(temperatureProvider)
                .getTemperatures();

        assertEquals("99 100 boiling 99.5 99 unboiling 10", app.execute());
    }


}
