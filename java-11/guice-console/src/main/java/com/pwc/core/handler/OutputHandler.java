package com.pwc.core.handler;

import com.pwc.core.entity.TemperatureAlert;

import java.util.List;

public interface OutputHandler {

    String convert(List<TemperatureAlert> alerts);
}
