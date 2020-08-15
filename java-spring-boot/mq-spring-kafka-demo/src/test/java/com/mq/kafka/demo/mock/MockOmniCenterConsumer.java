package com.mq.kafka.demo.mock;

import com.mq.kafka.demo.entity.Center;
import com.mq.kafka.demo.entity.City;

public class MockOmniCenterConsumer {

    private static String payload = "{\"schema\": \"sbLCBLaM78zz_R7f9ZrBGQ\", \"payload\": {\"Center_Status__c\": \"Active\", \"City_Name__c\": \"Beijing\", \"CreatedById\": \"0052800000443gIAAQ\", \"City_Code__c\": \"BJS\", \"City_Country__c\": \"China\", \"CreatedDate\": \"2019-08-30T03:03:05Z\", \"City_CNName__c\": \"\\u5317\\u4eac\", \"Center_Code__c\": \"DCNBJS21\", \"Center_Name__c\": \"BJ10FT1\"}, \"event\": {\"replayId\": 25712}}";

    public static String getPayload(){
        return payload;
    }

    public static City getCity(){

        City city = new City();
        city.setCountry("China")
                .setCityKey("BJS")
                .setCityName("Beijing")
                .setCityCname("北京")
                .setCreatedBy("0052800000443gIAAQ")
                .setId(1L);

        return city;
    }

    public static Center getCenter(){

       Center center = new Center();
       center
                .setId(1L)
                .setCityKey("BJS")
                .setCenterCode("DCNBJS21")
                .setCenterName("BJ10FT1")
                .setCreatedBy("0052800000443gIAAQ")
                .setCenterStatus(1);

       return center;
    }

}
