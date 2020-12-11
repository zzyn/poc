package com.pwc.core.handler;

import com.pwc.core.entity.BoilTriggerDirection;
import com.pwc.dataproviders.dto.Temperature;
import com.pwc.dataproviders.dto.Threshold;

public interface BoilTriggerHandler {

    BoilTriggerDirection execute(Threshold threshold, Temperature temperature);
}
