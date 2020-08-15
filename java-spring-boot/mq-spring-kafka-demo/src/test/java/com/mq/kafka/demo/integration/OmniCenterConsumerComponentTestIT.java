package com.mq.kafka.demo.integration;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mq.kafka.demo.Application;
import com.mq.kafka.demo.domain.CityAndCenterService;
import com.mq.kafka.demo.domain.LocalDateTimeNow;
import com.mq.kafka.demo.entity.Center;
import com.mq.kafka.demo.entity.City;
import com.mq.kafka.demo.listener.KmdDefaultListener;
import com.mq.kafka.demo.mock.MockOmniCenterConsumer;
import com.mq.kafka.demo.service.CenterService;
import com.mq.kafka.demo.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.messaging.Message;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@Slf4j
@RunWith(SpringRunner.class)
@DirtiesContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class OmniCenterConsumerComponentTestIT {

	private MessageListenerContainer messageListenerContainer;

	private static String RECEIVER_TOPIC = "OMNI-Center";

	@ClassRule
	public static EmbeddedKafkaRule rule = new EmbeddedKafkaRule(1, true, 1, RECEIVER_TOPIC);

	@Autowired
	public KafkaProperties kafkaProperties;

	@Autowired
	private KmdDefaultListener kmdDefaultListener;

	@Autowired
	private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

	@Autowired
	private CityAndCenterService cityAndCenterService;

	@MockBean
	private CityService cityService;

	@MockBean
	private CenterService centerService;

	@MockBean
	private LocalDateTimeNow now;

	@Mock
	private BaseMapper<City> cityBaseMapper;

	@Mock
	private BaseMapper<Center> centerBaseMapper;

	@Mock
	private Acknowledgment ack;

	@Mock
	private Message<String> payload;

	@Mock
	private Consumer<String, String> consumer;

	@Before
	public void setUp() throws Exception {

		EmbeddedKafkaBroker broker = rule.getEmbeddedKafka();

		messageListenerContainer = kafkaListenerEndpointRegistry.getListenerContainer("singleMsg");

		messageListenerContainer.setAutoStartup(false);

		messageListenerContainer.start();

		// wait until the partitions are assigned
		ContainerTestUtils.waitForAssignment(messageListenerContainer, broker.getPartitionsPerTopic());

	}

	@After
	public void tearDown() throws Exception {
		messageListenerContainer.stop();
	}

	@Test(expected = Exception.class)
	public void omni_center_listener_save_new_item_city_failed() throws Exception {

		doReturn(MockOmniCenterConsumer.getPayload())
				.when(payload)
				.getPayload();

		doReturn(LocalDateTime.now())
				.when(now)
				.now();

		doReturn(cityBaseMapper)
				.when(cityService)
				.getBaseMapper();

		doReturn(null)
				.when(cityBaseMapper)
				.selectOne(any());

		doReturn(false)
				.when(cityService)
				.save(any());

		kmdDefaultListener.singleMsgManualAckListener(payload, ack, consumer);
	}

	@Test(expected = Exception.class)
	public void omni_center_listener_save_new_item_center_failed() throws Exception {

		doReturn(MockOmniCenterConsumer.getPayload())
				.when(payload)
				.getPayload();

		doReturn(LocalDateTime.now())
				.when(now)
				.now();

		doReturn(cityBaseMapper)
				.when(cityService)
				.getBaseMapper();

		doReturn(null)
				.when(cityBaseMapper)
				.selectOne(any());

		doReturn(true)
				.when(cityService)
				.save(any());

		doReturn(centerBaseMapper)
				.when(centerService)
				.getBaseMapper();

		doReturn(null)
				.when(centerBaseMapper)
				.selectOne(any());

		doReturn(false)
				.when(centerService)
				.save(any());

		kmdDefaultListener.singleMsgManualAckListener(payload, ack, consumer);
	}

	@Test
	public void omni_center_listener_save_new_item_success() throws Exception {

		doReturn(MockOmniCenterConsumer.getPayload())
				.when(payload)
				.getPayload();

		doReturn(LocalDateTime.now())
				.when(now)
				.now();

		doReturn(cityBaseMapper)
				.when(cityService)
				.getBaseMapper();

		doReturn(null)
				.when(cityBaseMapper)
				.selectOne(any());

		doReturn(true)
				.when(cityService)
				.save(any());

		doReturn(centerBaseMapper)
				.when(centerService)
				.getBaseMapper();

		doReturn(null)
				.when(centerBaseMapper)
				.selectOne(any());

		doReturn(true)
				.when(centerService)
				.save(any());

		kmdDefaultListener.singleMsgManualAckListener(payload, ack, consumer);
	}

	@Test(expected = Exception.class)
	public void omni_center_listener_update_old_item_city_failed() throws Exception {

		doReturn(MockOmniCenterConsumer.getPayload())
				.when(payload)
				.getPayload();

		doReturn(LocalDateTime.now())
				.when(now)
				.now();

		doReturn(cityBaseMapper)
				.when(cityService)
				.getBaseMapper();

		doReturn(MockOmniCenterConsumer.getCity())
				.when(cityBaseMapper)
				.selectOne(any());

		doReturn(false)
				.when(cityService)
				.saveOrUpdate(any());

		kmdDefaultListener.singleMsgManualAckListener(payload, ack, consumer);
	}

	@Test(expected = Exception.class)
	public void omni_center_listener_update_old_item_center_failed() throws Exception {

		doReturn(MockOmniCenterConsumer.getPayload())
				.when(payload)
				.getPayload();

		doReturn(LocalDateTime.now())
				.when(now)
				.now();

		doReturn(cityBaseMapper)
				.when(cityService)
				.getBaseMapper();

		doReturn(MockOmniCenterConsumer.getCity())
				.when(cityBaseMapper)
				.selectOne(any());

		doReturn(true)
				.when(cityService)
				.saveOrUpdate(any());

		doReturn(centerBaseMapper)
				.when(centerService)
				.getBaseMapper();

		doReturn(MockOmniCenterConsumer.getCenter())
				.when(centerBaseMapper)
				.selectOne(any());

		doReturn(false)
				.when(centerService)
				.saveOrUpdate(any());

		kmdDefaultListener.singleMsgManualAckListener(payload, ack, consumer);
	}

	@Test
	public void omni_center_listener_update_old_item_success() throws Exception {

		doReturn(MockOmniCenterConsumer.getPayload())
				.when(payload)
				.getPayload();

		doReturn(LocalDateTime.now())
				.when(now)
				.now();

		doReturn(cityBaseMapper)
				.when(cityService)
				.getBaseMapper();

		doReturn(MockOmniCenterConsumer.getCity())
				.when(cityBaseMapper)
				.selectOne(any());

		doReturn(true)
				.when(cityService)
				.saveOrUpdate(any());

		doReturn(centerBaseMapper)
				.when(centerService)
				.getBaseMapper();

		doReturn(MockOmniCenterConsumer.getCenter())
				.when(centerBaseMapper)
				.selectOne(any());

		doReturn(true)
				.when(centerService)
				.saveOrUpdate(any());

		kmdDefaultListener.singleMsgManualAckListener(payload, ack, consumer);
	}

}
