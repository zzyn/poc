package com.pwc.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoilTriggerDirection {

    UP("boiling", 1),
    NONE("none", 0),
    DOWN("unboiling", -1);

    private String code;
    private int val;
}
