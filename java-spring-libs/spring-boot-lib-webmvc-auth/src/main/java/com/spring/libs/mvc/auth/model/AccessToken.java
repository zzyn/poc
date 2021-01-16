package com.spring.libs.mvc.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class AccessToken implements Serializable {

    @JsonProperty("id")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String id;

    @JsonProperty("uid")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String uid;

    @JsonProperty("jti")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String jti;

    @JsonProperty("provider")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String provider;

    @JsonProperty("realm")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String realm;

    @JsonProperty("sub")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String sub;

    @JsonProperty("app_id")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String appId;

    @JsonProperty("app_name")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String appName;

    @JsonProperty("type")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String type;

    @JsonProperty("iat")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Number issuedAt;

    @JsonProperty("exp")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Number expirationTime;
    
    @JsonIgnore
    private String rawData;
}
