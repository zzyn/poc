package com.webflux.cassandra.demo.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/*
 * JWT Fields
 * openid basic client profile url
 * https://openid.net/specs/openid-connect-basic-1_0.html#rfc.section.2.5
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityToken {

    @JsonProperty("sub")
    private String sub;

    @JsonProperty("name")
    private String name;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("nick_name")
    private String nickName;

    @JsonProperty("preferred_username")
    private String preferredUsername;

    @JsonProperty("profile")
    private String profile;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("website")
    private String website;

    @JsonProperty("email")
    private String email;

    @JsonProperty("email_verified")
    private Boolean emailVerified;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("birth")
    private String birthdate;

    @JsonProperty("zone_info")
    private String zoneInfo;

    @JsonProperty("locale")
    private String locale;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("phone_number_verified")
    private Boolean phoneNumberVerified;

    @JsonProperty("address")
    private AddressInfo address;

    @JsonProperty("updated_at")
    private Number updatedAt;

    @JsonProperty("market_region")
    private String marketRegion;

    @JsonProperty("tokens")
    private List<LegacyTokenInfo> tokens;

    @JsonProperty("iat")
    private Number issuedAt;

    @JsonProperty("exp")
    private Number expirationTime;

    public String getSub() {
        return sub;
    }

    public IdentityToken setSub(String sub) {
        this.sub = sub;
        return this;
    }

    public String getName() {
        return name;
    }

    public IdentityToken setName(String name) {
        this.name = name;
        return this;
    }

    public String getGivenName() {
        return givenName;
    }

    public IdentityToken setGivenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    public String getFamilyName() {
        return familyName;
    }

    public IdentityToken setFamilyName(String familyName) {
        this.familyName = familyName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public IdentityToken setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public IdentityToken setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getPreferredUsername() {
        return preferredUsername;
    }

    public IdentityToken setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public IdentityToken setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public IdentityToken setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public IdentityToken setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public IdentityToken setEmail(String email) {
        this.email = email;
        return this;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public IdentityToken setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public IdentityToken setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public IdentityToken setBirthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public String getZoneInfo() {
        return zoneInfo;
    }

    public IdentityToken setZoneInfo(String zoneinfo) {
        this.zoneInfo = zoneinfo;
        return this;
    }

    public String getLocale() {
        return locale;
    }

    public IdentityToken setLocale(String locale) {
        this.locale = locale;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public IdentityToken setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Boolean getPhoneNumberVerified() {
        return phoneNumberVerified;
    }

    public IdentityToken setPhoneNumberVerified(Boolean phoneNumberVerified) {
        this.phoneNumberVerified = phoneNumberVerified;
        return this;
    }

    public AddressInfo getAddress() {
        return address;
    }

    public IdentityToken setAddress(AddressInfo address) {
        this.address = address;
        return this;
    }

    public Number getUpdatedAt() {
        return updatedAt;
    }

    public IdentityToken setUpdatedAt(Number updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public List<LegacyTokenInfo> getTokens() {
        return tokens;
    }

    public IdentityToken setTokens(List<LegacyTokenInfo> tokens) {
        this.tokens = tokens;
        return this;
    }

    public String getMarketRegion() {
        return marketRegion;
    }

    public IdentityToken setMarketRegion(String marketRegion) {
        this.marketRegion = marketRegion;
        return this;
    }

    public Number getIssuedAt() {
        return issuedAt;
    }

    public IdentityToken setIssuedAt(Number issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    public Number getExpirationTime() {
        return expirationTime;
    }

    public IdentityToken setExpirationTime(Number expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }
}