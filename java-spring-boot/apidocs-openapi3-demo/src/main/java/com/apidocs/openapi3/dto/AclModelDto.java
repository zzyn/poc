package com.apidocs.openapi3.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AclModelDto implements Serializable {

    @JsonProperty("parent")
    private AclModelDto parent;
    @JsonProperty("children")
    private ArrayList<AclModelDto> children;
    @JsonProperty("level")
    private String level;
    @JsonProperty("identity")
    private String identity;
    @JsonProperty("value")
    private String value;
    @JsonProperty("description")
    private String description;
    @JsonProperty("purchaseType")
    private String purchaseType;
    @JsonProperty("isActived")
    private boolean isActived;
    @JsonProperty("isCurrentGroup")
    private boolean isCurrentGroup;
}
