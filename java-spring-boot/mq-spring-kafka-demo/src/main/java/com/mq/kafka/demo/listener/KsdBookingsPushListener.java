//package com.mq.kafka.demo.listener;
//
//import com.mq.kafka.demo.channel.ChannelConstant;
//import com.mq.kafka.demo.channel.inbound.BookingPushSink;
//import CityAndCenterService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.annotation.Transactional;
//
//@Slf4j
//@EnableBinding({BookingPushSink.class})
//@EnableTransactionManagement
//public class KsdBookingsPushListener {
//
//    private final CityAndCenterService cityAndCenterService;
//
//    @Autowired
//    public KsdBookingsPushListener(CityAndCenterService cityAndCenterService){
//
//        this.cityAndCenterService = cityAndCenterService;
//    }
//
//    //key.deserializer
//    //value.deserializer
//    @Transactional
//    @StreamListener(ChannelConstant.BOOKINGS_INPUT)
//    public void process(Object person) {
//        log.warn(person.toString());
//        System.out.println("Received: " + person);
//
//    }
//
//}
