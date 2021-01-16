package ${groupId}.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LegacyTokenInfo {

    @JsonProperty("value")
    private String value;
    @JsonProperty("version")
    private int version;
    @JsonProperty("session_count")
    private int sessionCount;
    @JsonProperty("expired_at")
    private Number expiredAt;

    public String getValue() {
        return value;
    }

    public LegacyTokenInfo setValue(String value) {
        this.value = value;
        return this;
    }

    public int getVersion() {
        return version;
    }

    public LegacyTokenInfo setVersion(int version) {
        this.version = version;
        return this;
    }

    public int getSessionCount() {
        return sessionCount;
    }

    public LegacyTokenInfo setSessionCount(int sessionCount) {
        this.sessionCount = sessionCount;
        return this;
    }

    public Number getExpiredAt() {
        return expiredAt;
    }

    public LegacyTokenInfo setExpiredAt(Number expiredAt) {
        this.expiredAt = expiredAt;
        return this;
    }
}