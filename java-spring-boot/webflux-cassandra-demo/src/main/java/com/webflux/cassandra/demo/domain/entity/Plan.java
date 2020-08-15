package com.webflux.cassandra.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.webflux.cassandra.demo.domain.Constants.LOCAL_DATETIME_PATTERN;

@JsonAutoDetect
@Table(value = "plan")
public class Plan implements Serializable {

    @PrimaryKey
    private UUID id;

    @Column(value = "student_key")
    private String studentKey;

    @Column(value = "product_key")
    private String productKey;

    @Column(value = "plan_key")
    private String planKey;

    @Column(value = "plan_type")
    private String planType;

    @Column(value = "trace_key")
    private String traceKey;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LOCAL_DATETIME_PATTERN)
    @Column(value = "created_time")
    private LocalDateTime createdTime;

    @Column(value = "created_by")
    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LOCAL_DATETIME_PATTERN)
    @Column(value = "last_updated_time")
    private LocalDateTime lastUpdatedTime;

    @Column(value = "last_updated_by")
    private String lastUpdatedBy;

    @Column(value = "state")
    private Integer state;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LOCAL_DATETIME_PATTERN)
    @Column(value = "start_time")
    private LocalDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LOCAL_DATETIME_PATTERN)
    @Column(value = "end_time")
    private LocalDateTime endTime;

    @Column(value = "map_root_key")
    private String mapRootKey;

    @Column(value = "map_root_version")
    private Integer mapRootVersion;

    @Column(value = "map_path")
    private String mapPath;

    @Column(value = "map_atomtic_key")
    private String mapAtomticKey;

    @Column(value = "map_atomtic_version")
    private Integer mapAtomticVersion;

    @Column(value = "learning_units")
    private List<String> learningUnits;

    public Plan() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStudentKey() {
        return studentKey;
    }

    public void setStudentKey(String studentKey) {
        this.studentKey = studentKey;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getPlanKey() {
        return planKey;
    }

    public void setPlanKey(String planKey) {
        this.planKey = planKey;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getTraceKey() {
        return traceKey;
    }

    public void setTraceKey(String traceKey) {
        this.traceKey = traceKey;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getMapRootKey() {
        return mapRootKey;
    }

    public void setMapRootKey(String mapRootKey) {
        this.mapRootKey = mapRootKey;
    }

    public Integer getMapRootVersion() {
        return mapRootVersion;
    }

    public void setMapRootVersion(Integer mapRootVersion) {
        this.mapRootVersion = mapRootVersion;
    }

    public String getMapPath() {
        return mapPath;
    }

    public void setMapPath(String mapPath) {
        this.mapPath = mapPath;
    }

    public String getMapAtomticKey() {
        return mapAtomticKey;
    }

    public void setMapAtomticKey(String mapAtomticKey) {
        this.mapAtomticKey = mapAtomticKey;
    }

    public Integer getMapAtomticVersion() {
        return mapAtomticVersion;
    }

    public void setMapAtomticVersion(Integer mapAtomticVersion) {
        this.mapAtomticVersion = mapAtomticVersion;
    }

    public List<String> getLearningUnits() {
        return learningUnits;
    }

    public void setLearningUnits(List<String> learningUnits) {
        this.learningUnits = learningUnits;
    }
}
