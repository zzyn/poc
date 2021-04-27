package com.kitchen.core.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum Temperature {
    ANY(0, "any"),
    HOT(1, "hot"),
    COLD(2, "cold"),
    FROZEN(3, "frozen");

    private Integer code;

    @JsonValue
    private String text;

    private static final Map<String, Temperature> textToEnum = new HashMap<>();
    private static final Map<Integer, Temperature> codeToEnum = new HashMap<>();
    static {
        for (Temperature state : values()) {
            textToEnum.put(state.text, state);
            codeToEnum.put(state.code, state);
        }
    }

    public static Temperature fromCode(Integer code){
        return Optional.ofNullable(codeToEnum.get(code))
                .orElse(codeToEnum.get(0));
    }

    @JsonCreator
    public static Temperature fromText(String text){
        return Optional.ofNullable(textToEnum.get(text))
                .orElse(textToEnum.get("any"));
    }

}
