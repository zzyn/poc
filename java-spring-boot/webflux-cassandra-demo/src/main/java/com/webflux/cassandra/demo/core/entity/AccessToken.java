package com.webflux.cassandra.demo.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken implements Serializable {

    @JsonProperty("sub")
    private String sub;

    @JsonProperty("app_id")
    private String appId;

    @JsonProperty("app_name")
    private String appName;

    @JsonProperty("type")
    private String type;

    @JsonProperty("roles")
    private List<String> roles;

    @JsonProperty("acls")
    private List<String> acls;

    @JsonProperty("iat")
    private Number issuedAt;

    @JsonProperty("exp")
    private Number expirationTime;

    public String getSub() {
        return sub;
    }

    public AccessToken setSub(String sub) {
        this.sub = sub;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public AccessToken setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getAppName() {
        return appName;
    }

    public AccessToken setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public String getType() {
        return type;
    }

    public AccessToken setType(String type) {
        this.type = type;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public AccessToken setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public List<String> getAcls() {
        return acls;
    }

    public AccessToken setAcls(List<String> acls) {
        this.acls = acls;
        return this;
    }

    public Number getIssuedAt() {
        return issuedAt;
    }

    public AccessToken setIssuedAt(Number issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    public Number getExpirationTime() {
        return expirationTime;
    }

    public AccessToken setExpirationTime(Number expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }
}
