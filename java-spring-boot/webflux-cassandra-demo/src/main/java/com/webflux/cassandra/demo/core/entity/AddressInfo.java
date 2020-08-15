package com.webflux.cassandra.demo.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * https://openid.net/specs/openid-connect-basic-1_0.html#AddressClaim
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressInfo {

    @JsonProperty("country")
    private String country;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("region")
    private String region;

    @JsonProperty("locality")
    private String locality;

    @JsonProperty("street_address")
    private String streetAddress;

    @JsonProperty("formatted")
    private String formatted;

    public String getCountry() {
        return country;
    }

    public AddressInfo setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AddressInfo setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public AddressInfo setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getLocality() {
        return locality;
    }

    public AddressInfo setLocality(String locality) {
        this.locality = locality;
        return this;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public AddressInfo setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public String getFormatted() {
        return formatted;
    }

    public AddressInfo setFormatted(String formatted) {
        this.formatted = formatted;
        return this;
    }
}