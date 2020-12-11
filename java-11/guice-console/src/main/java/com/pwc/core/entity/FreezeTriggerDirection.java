package com.pwc.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FreezeTriggerDirection {
    DOWN("freezing", -1),
    NONE("none", 0),
    UP("unfreezing", 1);

    private String code;
    private int val;
}
