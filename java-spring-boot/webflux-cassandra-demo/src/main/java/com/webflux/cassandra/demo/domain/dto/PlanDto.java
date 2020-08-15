package com.webflux.cassandra.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

import static com.webflux.cassandra.demo.domain.Constants.LOCAL_DATETIME_PATTERN;


@Getter
@Setter
public class PlanDto {

    private static final long serialVersionUID = 1L;

    private int productId;
    private String planBusinessKey;
    private int bucketId;
    private String studentKey;
    private UUID systemKey;

    private int planType;
    private int state;
    private String learningUnit;
    private String route;

    @JsonFormat(timezone = "UTC", pattern = LOCAL_DATETIME_PATTERN)
    private Date startTime;
    @JsonFormat(timezone = "UTC", pattern = LOCAL_DATETIME_PATTERN)
    private Date endTime;

    @JsonFormat(timezone = "UTC", pattern = LOCAL_DATETIME_PATTERN)
    private Date createdTime;
    private String createdBy;
    @JsonFormat(timezone = "UTC", pattern = LOCAL_DATETIME_PATTERN)
    private Date lastUpdatedTime;
    private String lastUpdatedBy;
}
