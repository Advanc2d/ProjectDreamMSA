package com.dream.mainservice.controller;

import java.security.Principal;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.mainservice.dto.Message;
import com.dream.mainservice.service.MainService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
	private final MainService service;

	// 로그인 하지 않았을 시 return
	@GetMapping("/")
	public String main(Model model) throws Exception {
		log.info("come on man~");
		System.out.println(service.getProductList());
		model.addAttribute("product", service.getProductList());
		return "main2";
	}

	// 로그인 되어있을 시 return
	@GetMapping("/menu")
	public String menu(Principal principal, Model model) throws Exception {
		log.info("Hi man~!");
		System.out.println(service.getProductList());
		System.out.println("권한 : " + principal);
		if (principal != null) {
			JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
			log.info("toString : " + token.getTokenAttributes().toString());
			model.addAttribute("list", token.getTokenAttributes());
			model.addAttribute("product", service.getProductList());
		}
		return "main";
	}
	
	   // kafka producer
	   @Autowired
	   private final KafkaTemplate<String, Message> kafkaTemplate;

	   @Value(value = "${kafka.topic_name}")
	   private String kafkaTopicName;
	   
	   @Value(value = "${kafka.server_endpoint}")
	   private String kafkendPoinst;
	   
	   String status = "";


	   @RequestMapping(value = "/kafka", method = RequestMethod.POST)
	   public ResponseEntity<String> sendMessage(@RequestBody Message message) {
	      log.info("----------------------main/kafka/ URL 작동-----------------------");
	      log.info("메세지 전동 된다. {}", message);

	      ListenableFuture<SendResult<String, Message>> future = this.kafkaTemplate.send(kafkaTopicName, message);
	      log.info("여기는 넘어오냐?");
	      future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {

	         @Override
	         public void onSuccess(SendResult<String, Message> result) {
	            status = "Message send successfully, 메시지가 성공적으로 전송 됨.";
	            log.info("메시지가 성공적으로 전송됨. successfully sent message = {}, with offset = {}", message,
	                  result.getRecordMetadata().offset());
	         }

	         @Override
	         public void onFailure(Throwable ex) {
	            log.info("Failed to send message = {}, error = {}", message, ex.getMessage());
	            status = "Message sending failed = 메시지 전송 실패...";
	         }
	      });

	      return ResponseEntity.ok(status);
	   }   
}