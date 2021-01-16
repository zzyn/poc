package com.spring.libs.mvc.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LegacyTokenInfo implements Serializable {

    @JsonProperty("value")
    private String value;

    @JsonProperty("version")
    private int version;

    @JsonProperty("session_count")
    private int sessionCount;

    @JsonProperty("expired_at")
    private Number expiredAt;
}