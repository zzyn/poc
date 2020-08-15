//package com.mq.kafka.demo.listener;
//
//import com.mq.kafka.demo.channel.inbound.BookingPollSink;
//import com.mq.kafka.demo.domain.CityAndCenterService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.binder.DefaultPollableMessageSource;
//import org.springframework.cloud.stream.binder.PollableMessageSource;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.integration.StaticMessageHeaderAccessor;
//import org.springframework.integration.acks.AckUtils;
//import org.springframework.integration.acks.AcknowledgmentCallback;
//import org.springframework.messaging.MessageHeaders;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.util.MimeType;
//
//import java.util.UUID;
//import java.util.concurrent.atomic.AtomicBoolean;
//import static com.mq.kafka.demo.channel.ChannelConstant.BOOKINGS_INPUT;
//
//@Slf4j
//@EnableBinding({BookingPollSink.class})
//@EnableTransactionManagement
//@Component
//public class KsdBookingsPollListener implements ApplicationRunner {
//
//    private final CityAndCenterService cityAndCenterService;
//
//    private final PollableMessageSource input;
//
//    @Autowired
//    public KsdBookingsPollListener(CityAndCenterService cityAndCenterService, @Qualifier(value = BOOKINGS_INPUT) PollableMessageSource input) {
//        this.cityAndCenterService = cityAndCenterService;
//        this.input = input;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//
//        DefaultPollableMessageSource defaultPollableMessageSource = null;
//
//        if(input instanceof DefaultPollableMessageSource){
//            defaultPollableMessageSource = (DefaultPollableMessageSource)input;
////            defaultPollableMessageSource.setErrorMessageStrategy((payload, attributes)-> {
////                log.error(payload.getMessage(), payload);
////                return null;
////            });
////            defaultPollableMessageSource.setErrorChannel(null);
//        }
//
//        while (true){
//            boolean result = false;
//            result = defaultPollableMessageSource.poll(messageHandler->{
//                UUID id = StaticMessageHeaderAccessor.getId(messageHandler);
//                MimeType contentType = StaticMessageHeaderAccessor.getContentType(messageHandler);
//                Long timestamp = StaticMessageHeaderAccessor.getTimestamp(messageHandler);
//                AcknowledgmentCallback callback = StaticMessageHeaderAccessor.getAcknowledgmentCallback(messageHandler);
//                callback.noAutoAck();
//                if(!callback.isAcknowledged()){
//                    //payload which include payload and schema
//                    Object payload = messageHandler.getPayload();
//                    //kafka headers
//                    MessageHeaders headers = messageHandler.getHeaders();
//
//                    //insert and update database
//                    Boolean isSuccess = false;
//                    try {
//                        isSuccess = cityAndCenterService.upsert(null);
//                    } catch (Exception e) {
//                        log.error(e.getMessage(), e);
//                    }
//
//                    if(isSuccess){
//                        AckUtils.accept(callback);
//                        log.info("accept message: id={} timestamp={}", id, timestamp);
//                    } else {
//                        AckUtils.requeue(callback);
//                        log.warn("requeue message: id={} timestamp={}", id, timestamp);
//                    }
//                }
//
//            }, ParameterizedTypeReference.forType(Object.class));
//
//            if(result){
//                log.info("success.");
//            } else {
//                log.warn("wait {} seconds", 1);
//                Thread.sleep(1_000);
//            }
//        }
//    }
//}
