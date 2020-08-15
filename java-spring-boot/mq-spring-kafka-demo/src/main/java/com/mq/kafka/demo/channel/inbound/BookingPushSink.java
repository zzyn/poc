package com.mq.kafka.demo.channel.inbound;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

import static com.mq.kafka.demo.channel.ChannelConstant.BOOKINGS_INPUT;

public interface BookingPushSink {

    @Input(value = BOOKINGS_INPUT)
    SubscribableChannel input();
}
