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
public class OrderKafkaService {
	private static Message ms;
	
	@KafkaListener(topics = "${kafka.topic_name}", groupId = "${kafka.group_id}")
	public void listner(String message) throws JsonMappingException, JsonProcessingException {
		log.info("Received message = {}", message);

		ObjectMapper mapper = new ObjectMapper();
		OrderProductVO vo = new OrderProductVO();
		ms = mapper.readValue(message, Message.class);

		vo.setProNo(Integer.parseInt(ms.getProNo()));

		log.info("되나?");
	}
	
	public Message getMessage() {
		return ms;
	}
}