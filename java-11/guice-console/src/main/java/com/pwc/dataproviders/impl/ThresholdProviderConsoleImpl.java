package com.pwc.dataproviders.impl;

import com.pwc.dataproviders.ThresholdProvider;
import com.pwc.dataproviders.dto.Threshold;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.text.ParseException;

@Slf4j
public class ThresholdProviderConsoleImpl implements ThresholdProvider {

    @Override
    public Threshold getThreshold() {
        String freezingThreshold;
        do {
            System.out.println("input freezing threshold:");
            freezingThreshold = System.console().readLine();
        } while (!isNumber(freezingThreshold));
        System.out.println(String.format("freezing threshold: %s", freezingThreshold));

        String boilingThreshold;
        do {
            System.out.println("input boiling threshold:");
            boilingThreshold = System.console().readLine();
        } while (!isNumber(boilingThreshold));
        System.out.println(String.format("boiling threshold: %s", boilingThreshold));

        String fluctuationValue;
        do {
            System.out.println("input fluctuation value:");
            fluctuationValue = System.console().readLine();
        } while (!isNumber(fluctuationValue));
        System.out.println(String.format("fluctuation value: %s", fluctuationValue));

        return getThreshold(freezingThreshold, boilingThreshold, fluctuationValue);
    }

    private boolean isNumber(String source) {
        boolean result = false;
        try {
            NumberFormat.getInstance().parse(source.trim());
            result = true;
        }
        catch (NullPointerException e){
            log.warn(e.getMessage());
        }
        catch (ParseException e){
            log.warn(e.getMessage());
        }
        return result;
    }
}
