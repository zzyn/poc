package com.mq.kafka.demo.message;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * {"schema": "sbLCBLaM78zz_R7f9ZrBGQ", "payload": {"Center_Status__c": "Active", "City_Name__c": "Beijing", "CreatedById": "0052800000443gIAAQ", "City_Code__c": "BJS", "City_Country__c": "China", "CreatedDate": "2019-08-30T03:03:05Z", "City_CNName__c": "\u5317\u4eac", "Center_Code__c": "DCNBJS21", "Center_Name__c": "BJ10FT1"}, "event": {"replayId": 25712}}
 */

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class OmniCenterMessage {

    @JsonProperty("schema")
    private String schema;

    @JsonProperty("payload")
    private OmniCenterPayload payload;

    @JsonProperty("event")
    private Event event;
}


