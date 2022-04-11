package com.dream.confirm.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.dream.confirm.dto.SendMessage;
import com.dream.confirm.dto.confirmDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

//consumer
@Component
@Slf4j
public class ConfirmKafkaService {
	private static SendMessage ms;

	@KafkaListener(topics = "my-topic2", groupId = "${kafka.group_id}")
	public void listner(String message) throws JsonMappingException, JsonProcessingException {
		log.info("여기 오냐잉 = {}", message);

		ObjectMapper mapper = new ObjectMapper();
		confirmDto vo = new confirmDto();
		ms = mapper.readValue(message, SendMessage.class);

		vo.setUserId(ms.getUserId());

		log.info("되나?");
	}

	public SendMessage getMessage() {
		return ms;
	}
}
