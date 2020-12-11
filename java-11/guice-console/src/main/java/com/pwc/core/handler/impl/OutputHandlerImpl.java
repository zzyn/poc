package com.pwc.core.handler.impl;

import com.pwc.core.entity.TemperatureAlert;
import com.pwc.core.handler.OutputHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OutputHandlerImpl implements OutputHandler {

    @Override
    public String convert(List<TemperatureAlert> alerts) {

        if (Objects.isNull(alerts) || alerts.isEmpty()) {
            return "";
        }
        List<String> union = new ArrayList<>();
        alerts.stream().forEachOrdered(x -> {
            union.add(x.getUnion());
        });
        return String.join(" ", union);
    }
}
