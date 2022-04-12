package com.dream.orderService.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.dream.orderService.domain.Message;
import com.dream.orderService.domain.OrderProductVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
//Consumer
public class OrderKafkaService {
	private static Message ms;
	
	@KafkaListener(topics = "my-topic1", groupId = "${kafka.group_id}")
	public void listner(String message) throws JsonMappingException, JsonProcessingException {
		log.info("Order Kafka Listener message = {}", message);

		ObjectMapper mapper = new ObjectMapper();
		OrderProductVO vo = new OrderProductVO();
		ms = mapper.readValue(message, Message.class);
		log.info(ms+"-------------");
		vo.setProNo(Integer.parseInt(ms.getProNo()));

		log.info("Order Kafka Listener Finish");
	}
	
	public Message getMessage() {
		return ms;
	}
}