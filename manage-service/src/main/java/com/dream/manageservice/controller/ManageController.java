package com.dream.manageservice.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dream.manageservice.dto.Datadto;
import com.dream.manageservice.dto.StatusDto;
import com.dream.manageservice.dto.StatusMessage;
import com.dream.manageservice.service.ManageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ManageController {
private final ManageService statusService;
	
	@RolesAllowed({ "MANAGER" })
    @GetMapping("/wait")
	   public String wait(Model model, Principal principal) throws Exception {
		log.info("---------------------- status/wait URL로 이동  -----------------------");
	      List<StatusDto> list = statusService.getStatusList();
	      JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
	      model.addAttribute("keycloakList", token.getTokenAttributes());
	      model.addAttribute("list", list);
	      return "statusList";
	   }
	
	@RolesAllowed({ "MANAGER" })
	@PostMapping({"/update"})
	@ResponseBody
	public void update(@RequestParam Map<String, Object> data) throws JsonProcessingException {
		log.info("---------------------- save/update URL 작동 -----------------------");
		String json = data.get("data").toString();
		System.out.println("json = " + json);
		ObjectMapper mapper = new ObjectMapper();
		List<Datadto> datas = mapper.readValue(json, new TypeReference<ArrayList<Datadto>>() {
		});
		for (Datadto data1 : datas) {
			statusService.getUpdate(data1);
		}
	}
	
	
	// kafka producer
	   @Autowired
	   private final KafkaTemplate<String, StatusMessage> kafkaTemplate;

	   @Value(value = "${kafka.topic_name}")
	   private String kafkaTopicName;
	   
	   @Value(value = "${kafka.server_endpoint}")
	   private String kafkendPoinst;

	   String status = "";
	   
	 @RequestMapping(value = "/kafka", method = RequestMethod.POST)
	   public ResponseEntity<String> sendMessage(@RequestBody StatusMessage smsg) {
	      log.info("----------------------status/kafka/ URL 작동-----------------------");
	      log.info("메세지 전동 된다. {}", smsg);
	      log.info("22222222222222222222222222 " +  kafkaTopicName);
	      
	      ListenableFuture<SendResult<String, StatusMessage>> future = this.kafkaTemplate.send(kafkaTopicName, smsg);
	      
	      future.addCallback(new ListenableFutureCallback<SendResult<String, StatusMessage>>() {

	         @Override
	         public void onSuccess(SendResult<String, StatusMessage> result) {
	            status = "Message send successfully, 메시지가 성공적으로 전송 됨.";
	            log.info("메시지가 성공적으로 전송됨. successfully sent message = {}, with offset = {}", smsg,
	                  result.getRecordMetadata().offset());
	         }

	         @Override
	         public void onFailure(Throwable ex) {
	            log.info("Failed to send message = {}, error = {}", smsg, ex.getMessage());
	            status = "Message sending failed = 메시지 전송 실패...";
	         }
	      });

	      return ResponseEntity.ok(status);
	   }   
}
