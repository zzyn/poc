package com.mq.kafka.demo.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import static com.mq.kafka.demo.message.MessageConstants.LOCAL_DATETIME_PATTERN;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class OmniCenterPayload {

    @JsonProperty("City_Code__c")
    private String cityCode;

    @JsonProperty("Center_Status__c")
    private String centerStatus;

    @JsonProperty("City_Name__c")
    private String cityName;

    @JsonProperty("City_CNName__c")
    private String cityCName;

    @JsonProperty("City_Country__c")
    private String country;

    @JsonProperty("Center_Code__c")
    private String centerCode;

    @JsonProperty("Center_Name__c")
    private String centerName;

    @JsonProperty("CreatedById")
    private String createdBy;

    @JsonProperty("CreatedDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LOCAL_DATETIME_PATTERN)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdDate;
}
