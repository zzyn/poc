package com.mq.kafka.demo.unit;

import com.mq.kafka.demo.domain.LocalDateTimeNow;
import com.mq.kafka.demo.domain.impl.LocalDateTimeNowImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class LocalDateTimeNowUnitTest {


    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private LocalDateTimeNow localDateTimeNow;

    @Before
    public void setup() throws Exception {

        localDateTimeNow = new LocalDateTimeNowImpl();
    }

    @Test
    public void now_success() throws Exception{

        LocalDateTime now = localDateTimeNow.now();
        Assert.assertNotNull(now);
    }
}