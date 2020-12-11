package com.pwc.dataproviders.impl;

import com.pwc.dataproviders.ThresholdProvider;
import com.pwc.dataproviders.dto.Threshold;

public class ThresholdProviderDebugImpl implements ThresholdProvider {

    @Override
    public Threshold getThreshold() {
        return getThreshold("0","100", "0.5");
    }
}
