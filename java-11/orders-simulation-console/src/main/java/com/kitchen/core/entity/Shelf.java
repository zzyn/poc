package com.kitchen.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@ToString()
public class Shelf {
    private String name;
    private Temperature allowableTemperature;
    private Integer capacity;
    private Integer shelfDecayModifier;
}
