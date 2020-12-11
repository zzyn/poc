package com.pwc.dataproviders;

import com.pwc.dataproviders.dto.Temperature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public interface TemperatureProvider {

    List<Temperature> getTemperatures();

    default List<Temperature> getTemperatures(String input) {
        Objects.requireNonNull(input,"input");
        List<Temperature> list = new ArrayList<>();
        Arrays.stream(input.trim().split(" ")).forEachOrdered(x->{
            list.add(new Temperature().setRaw(x).setVal(Float.valueOf(x)));
        });
        return list;
    }
}
