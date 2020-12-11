package com.pwc.core.usercase;

import com.pwc.core.entity.TemperatureAlert;
import com.pwc.dataproviders.dto.Temperature;
import com.pwc.dataproviders.dto.Threshold;

import java.util.List;

public interface AddAlertsForTemperatureUserCase {

    List<TemperatureAlert> execute(Threshold threshold,  List<Temperature> temperatures);
}
