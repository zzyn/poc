package com.mq.kafka.demo.domain.impl;

import com.mq.kafka.demo.domain.LocalDateTimeNow;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LocalDateTimeNowImpl implements LocalDateTimeNow {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
