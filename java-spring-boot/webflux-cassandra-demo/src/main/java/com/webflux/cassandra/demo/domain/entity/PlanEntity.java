package com.webflux.cassandra.demo.domain.entity;

import com.datastax.driver.core.DataType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Table(value = "studentplan")
public class PlanEntity{

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, ordinal = 1)
    private int productId;

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, ordinal = 2)
    private String planBusinessKey;

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, ordinal = 3)
    private Integer bucketId;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordinal = 1)
    @Column(value = "studentkey")
    private String studentKey;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordinal = 2)
    private UUID systemKey;

    @Column(value = "plantype")
    private Integer planType;

    @Column(value = "state")
    private Integer state;

    @Column(value = "learningunit")
    private String learningUnit;

    @Column(value = "route")
    private String route;

    @Column(value = "starttime")
    @CassandraType(type = DataType.Name.TIMESTAMP)
    private Date startTime;

    @Column(value = "endtime")
    @CassandraType(type = DataType.Name.TIMESTAMP)
    private Date endTime;

    @Column(value = "createdtime")
    @CassandraType(type = DataType.Name.TIMESTAMP)
    private Date createdTime;

    @Column(value = "createdby")
    private String createdBy;

    @Column(value = "lastupdatedtime")
    @CassandraType(type = DataType.Name.TIMESTAMP)
    private Date lastUpdatedTime;

    @Column(value = "lastupdatedby")
    private String lastUpdatedBy;
}
