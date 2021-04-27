package com.kitchen.dataproviders.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString()
public class Order {

    @EqualsAndHashCode.Include
    private String id;
    private String name;
    private String temp;
    private int shelfLife;
    private double decayRate;
    private long startTimestamp = 0;
    private long orderAge = 0;
    public long getOrderAge() {
        return (orderAge = (System.currentTimeMillis() - startTimestamp) / 1000);
    }
}
