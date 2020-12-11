package com.pwc.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@ToString()
public class TemperatureAlert {

    private String temperature;
    private List<String> alerts;

    public String getUnion() {
        List<String> union = new ArrayList<>();
        if (Objects.nonNull(temperature)) {
            union.add(temperature);
        }
        if (Objects.nonNull(alerts)) {
            union.addAll(alerts);
        }
        return String.join(" ", union);
    }
}
