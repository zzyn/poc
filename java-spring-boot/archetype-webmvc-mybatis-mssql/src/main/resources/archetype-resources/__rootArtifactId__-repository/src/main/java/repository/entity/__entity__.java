package ${groupId}.repository.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonAutoDetect
public class ${entity} implements Serializable {

    private UUID id;
    private String name;
    private String createdBy;
    private LocalDateTime createdStamp;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedStamp;

    public UUID getId() {
        return id;
    }

    public ${entity} setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ${entity} setName(String name) {
        this.name = name;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ${entity} setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getCreatedStamp() {
        return createdStamp;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public ${entity} setCreatedStamp(LocalDateTime createdStamp) {
        this.createdStamp = createdStamp;
        return this;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public ${entity} setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getLastUpdatedStamp() {
        return lastUpdatedStamp;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public ${entity} setLastUpdatedStamp(LocalDateTime lastUpdatedStamp) {
        this.lastUpdatedStamp = lastUpdatedStamp;
        return this;
    }
}