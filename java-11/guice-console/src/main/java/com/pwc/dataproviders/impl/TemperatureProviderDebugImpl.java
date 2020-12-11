package com.pwc.dataproviders.impl;

import com.pwc.dataproviders.TemperatureProvider;
import com.pwc.dataproviders.dto.Temperature;

import java.util.List;

public class TemperatureProviderDebugImpl implements TemperatureProvider {
    @Override
    public List<Temperature> getTemperatures() {

        //4.0 1.0 0.5 0.0 -0.5 0.0 0.5 0.0 -2.0 0.0 0.5 0.6 2.0
        //String input = "4.0 1.0 0.5 0.0 -0.5 0.0 0.5 0.0 -2.0 0.0 0.5 0.6 2.0";
        //5.0 -0.5 0.5 -0.2 100 101
        String input = "5.0 -0.5 0.5 -0.2 100 101";
        //0.0 0.3 0.5 0.4 0.7
        //String input = "0.0 0.3 0.5 0.4 0.7";

        return getTemperatures(input);
    }
}
