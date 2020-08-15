package com.mq.kafka.demo.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @JsonProperty("replayId")
    private Integer replayId;
}
