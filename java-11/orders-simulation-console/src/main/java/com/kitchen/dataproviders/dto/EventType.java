package com.kitchen.dataproviders.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum EventType {

    INGEST_ORDER(0, "Ingest_Order"),
    KITCHEN_ACCEPT_ORDER(1, "Kitchen_Accept_Order"),
    KITCHEN_COOKED_ORDER(2, "Kitchen_Cooked_Order"),
    KITCHEN_PUT_ORDER_TO_SHELVES(3, "Kitchen_Put_Order_To_Shelves"),
    COURIER_ACCEPT_ORDER(4, "Courier_Accept_Order"),
    COURIER_PICKUP_ORDER(5, "Courier_Pickup_Order"),
    COURIER_DELIVERY_ORDER(6, "Courier_Delivery_Order"),
    COURIER_CANCEL_ORDER(7, "Courier_Cancel_Order"),
    APP_EXIT(100, "App_Exit"),
    UNKNOWN(999, "Unknown");

    private static final Map<String, EventType> textToEnum = new HashMap<>();
    private static final Map<Integer, EventType> codeToEnum = new HashMap<>();

    static {
        for (EventType state : values()) {
            textToEnum.put(state.text, state);
            codeToEnum.put(state.code, state);
        }
    }

    private final Integer code;
    @JsonValue
    private final String text;

    public static EventType fromCode(Integer code) {
        return Optional.ofNullable(codeToEnum.get(code))
                .orElse(codeToEnum.get(999));
    }

    @JsonCreator
    public static EventType fromText(String text) {
        return Optional.ofNullable(textToEnum.get(text))
                .orElse(textToEnum.get("Unknown"));
    }

}
