package com.pwc.core.handler;

import com.pwc.dataproviders.dto.Temperature;
import com.pwc.dataproviders.dto.Threshold;

import java.util.List;

public interface AlertHandler {

    boolean calcFreezeAlert(Threshold threshold, Temperature temperature, boolean isTrigger, List<String> alerts);

    boolean calcBoilAlert(Threshold threshold, Temperature temperature, boolean isTrigger, List<String> alerts);

}
