package com.kitchen.dataproviders.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.UUID;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Builder
public class Event {

    @EqualsAndHashCode.Include
    private UUID id;
    private long date;
    private String creator;
    private EventType action;
    private Order order;
    private String reason;
}
