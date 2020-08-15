package com.mq.kafka.demo.channel.inbound;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.binder.PollableMessageSource;

import static com.mq.kafka.demo.channel.ChannelConstant.BOOKINGS_INPUT;

public interface BookingPollSink {

    @Qualifier(value = BOOKINGS_INPUT)
    @Input(value = BOOKINGS_INPUT)
    PollableMessageSource input();
}
