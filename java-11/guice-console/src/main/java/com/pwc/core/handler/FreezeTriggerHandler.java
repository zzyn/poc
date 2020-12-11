package com.pwc.core.handler;

import com.pwc.core.entity.FreezeTriggerDirection;
import com.pwc.dataproviders.dto.Temperature;
import com.pwc.dataproviders.dto.Threshold;

public interface FreezeTriggerHandler {

    FreezeTriggerDirection execute(Threshold threshold, Temperature temperature);
}
