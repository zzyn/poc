package com.mq.kafka.demo.listener;

import com.mq.kafka.demo.domain.CityAndCenterService;
import com.mq.kafka.demo.message.OmniCenterMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KmdDefaultListener {

    private final ObjectMapper mapper;
    private final CityAndCenterService cityAndCenterService;

    @Autowired
    public KmdDefaultListener(ObjectMapper mapper, CityAndCenterService cityAndCenterService) {
        this.mapper = mapper;
        this.cityAndCenterService = cityAndCenterService;
    }

    @KafkaListener(id = "singleMsg"
            , topics = "OMNI-Center"
            , clientIdPrefix = "kmd"
            , groupId = "center1"
            , containerFactory = "batchFactory"
    )
    public void singleMsgManualAckListener(Message<String> payload
            , Acknowledgment ack
            , Consumer<String, String> consumer) throws Exception {

        String data = payload.getPayload();

        OmniCenterMessage msg = mapper.readValue(data, OmniCenterMessage.class);

        cityAndCenterService.upsert(msg);

        ack.acknowledge();
    }


//    @KafkaListener(id = "batchMsg", clientIdPrefix = "kmd-", groupId = "kmd-kafka-consumer" ,topics = "OMNI-Center", containerFactory = "batchFactory")
//    public void batchMessageManualAckListener(
//            List<Message<String>> list
//            , Acknowledgment ack
//            , Consumer<String, String> consumer) {
//
//        Object data = list;
//    }


//    @KafkaListener(id = "batchRecord", clientIdPrefix = "kmd-", groupId = "kmd-kafka-consumer" ,topics = "OMNI-Center", containerFactory = "batchFactory")
//    public void batchConsumerRecordManualAckListener(List<ConsumerRecord<String, String>> list, Acknowledgment ack, Consumer<String, String> consumer) {
//
//       Object data = list;
//    }

}
