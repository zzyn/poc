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
import java.util.List;

/**
 * JWT Fields
 * openid basic client profile url
 * https://openid.net/specs/openid-connect-basic-1_0.html#rfc.section.2.5
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityToken implements Serializable {

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

    @JsonProperty("name")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String name;

    @JsonProperty("given_name")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String givenName;

    @JsonProperty("family_name")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String familyName;

    @JsonProperty("middle_name")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String middleName;

    @JsonProperty("nick_name")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String nickName;

    @JsonProperty("preferred_username")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String preferredUsername;

    @JsonProperty("profile")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String profile;

    @JsonProperty("picture")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String picture;

    @JsonProperty("website")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String website;

    @JsonProperty("email")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String email;

    @JsonProperty("email_verified")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Boolean emailVerified;

    @JsonProperty("gender")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String gender;

    @JsonProperty("birth")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String birthDate;

    @JsonProperty("zone_info")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String zoneInfo;

    @JsonProperty("locale")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String locale;

    @JsonProperty("phone_number")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String phoneNumber;

    @JsonProperty("phone_number_verified")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Boolean phoneNumberVerified;

    @JsonProperty("address")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private AddressInfo address;

    @JsonProperty("updated_at")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Number updatedAt;

    @JsonProperty("market_region")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String marketRegion;

    @JsonProperty("business_unit")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String businessUnit;

    @JsonProperty("state")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String state;

    @JsonProperty("tokens")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<LegacyTokenInfo> tokens;

    @JsonProperty("iat")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Number issuedAt;

    @JsonProperty("exp")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Number expirationTime;

    @JsonIgnore
    private String rawData;
}
