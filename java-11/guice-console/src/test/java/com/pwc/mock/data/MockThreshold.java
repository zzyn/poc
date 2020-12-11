package com.pwc.mock.data;

import com.pwc.dataproviders.dto.Threshold;

public class MockThreshold {

    public static Threshold getThreshold() {
        return new Threshold()
                .setFreezing(0f)
                .setBoiling(100f)
                .setFluctuation(0.5f);
    }
}
