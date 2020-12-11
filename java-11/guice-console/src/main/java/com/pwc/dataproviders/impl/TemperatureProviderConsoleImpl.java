package com.pwc.dataproviders.impl;

import com.pwc.dataproviders.TemperatureProvider;
import com.pwc.dataproviders.dto.Temperature;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

@Slf4j
public class TemperatureProviderConsoleImpl implements TemperatureProvider {

    @Override
    public List<Temperature> getTemperatures() {

        String temperatures;
        do {
            System.out.println("input temperatures:");
            temperatures = System.console().readLine();
        } while (!isNumberSequence(temperatures));
        System.out.println(String.format("temperatures: %s", temperatures));

        return getTemperatures(temperatures);
    }

    private boolean isNumberSequence(String source) {
        boolean result = true;
        try {
            String[] items = source.trim().split(" ");
            for (String item : items) {
                NumberFormat.getInstance().parse(item);
            }
        } catch (NullPointerException e) {
            log.warn(e.getMessage());
            result = false;
        } catch (ParseException e) {
            log.warn(e.getMessage());
            result = false;
        }
        return result;
    }
}
