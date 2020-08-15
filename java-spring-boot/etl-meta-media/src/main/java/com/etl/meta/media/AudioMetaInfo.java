package com.etl.meta.media;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class AudioMetaInfo implements Serializable {

    private String contentType;

    private Long size;

    private String duration;
}
