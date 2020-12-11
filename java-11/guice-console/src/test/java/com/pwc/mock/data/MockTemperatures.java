package com.pwc.mock.data;

import com.pwc.dataproviders.dto.Temperature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MockTemperatures {

    public static List<Temperature> getList(String input) {
        Objects.requireNonNull(input,"input");
        List<Temperature> list = new ArrayList<>();
        Arrays.stream(input.trim().split(" ")).forEachOrdered(x->{
            list.add(new Temperature().setRaw(x).setVal(Float.valueOf(x)));
        });
        return list;
    }
}
