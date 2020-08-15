package com.mq.kafka.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import lombok.experimental.Accessors;

import static com.mq.kafka.demo.message.MessageConstants.LOCAL_DATETIME_PATTERN;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Center implements Serializable {


    @TableId(value ="id", type = IdType.AUTO)
    private Long id;

    @TableField("city_key")
    private String cityKey;

    @TableField("center_code")
    private String centerCode;

    @TableField("center_name")
    private String centerName;

    @TableField("center_status")
    private Integer centerStatus;

    @TableField("created_by")
    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LOCAL_DATETIME_PATTERN)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @TableField("created_timestamp")
    private LocalDateTime createdTimestamp;

    @TableField("updated_by")
    private String updatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LOCAL_DATETIME_PATTERN)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @TableField("updated_timestamp")
    private LocalDateTime updatedTimestamp;


}
