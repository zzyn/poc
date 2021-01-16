package com.spring.libs.mvc.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * https://openid.net/specs/openid-connect-basic-1_0.html#AddressClaim
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressInfo implements Serializable {

    @JsonProperty("country")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String country;

    @JsonProperty("postal_code")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String postalCode;

    @JsonProperty("region")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String region;

    @JsonProperty("locality")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String locality;

    @JsonProperty("street_address")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String streetAddress;

    @JsonProperty("formatted")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String formatted;
}