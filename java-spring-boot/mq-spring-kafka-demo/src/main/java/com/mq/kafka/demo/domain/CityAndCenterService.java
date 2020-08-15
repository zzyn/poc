package com.mq.kafka.demo.domain;

import com.mq.kafka.demo.message.OmniCenterMessage;

public interface CityAndCenterService {

    void upsert(OmniCenterMessage omniCenterMessage) throws Exception;
}
