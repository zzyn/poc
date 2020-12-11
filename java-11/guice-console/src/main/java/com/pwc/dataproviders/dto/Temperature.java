package com.pwc.dataproviders.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@ToString()
public class Temperature {

    private String raw;

    private float val;
}
