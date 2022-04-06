package com.dream.approveservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.dream.approveservice.dto.StatusMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApproveKafkaService {

	private static StatusMessage orderNo;
	
//	@KafkaListener(topics="my-topic", groupId="consumer-1")	
	@KafkaListener(topics = "${kafka.topic_name}", groupId = "${kafka.group_id}")
	public void listner(String smsg) throws JsonMappingException, JsonProcessingException {
		log.info("Received message = {}", smsg);

		ObjectMapper mapper = new ObjectMapper();
//		loanListVO vo = new loanListVO();
		orderNo = mapper.readValue(smsg, StatusMessage.class);

//		vo.setProNo(Integer.parseInt(orderNo.getOrderNo()));

		log.info(orderNo+ "???");
	}

	public StatusMessage getMessage() {
		return orderNo;
	}
}
