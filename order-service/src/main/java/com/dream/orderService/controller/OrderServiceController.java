package com.dream.orderService.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dream.orderService.domain.Message;
import com.dream.orderService.domain.OrderOrderVO;
import com.dream.orderService.domain.SendMessage;
import com.dream.orderService.service.OrderKafkaService;
import com.dream.orderService.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderServiceController {
	private final OrderService service;
	private static int proNo;
	private final OrderKafkaService ofs;
	private static Message ms;

	@GetMapping("/detail")
	public String loanPage(Model model, Principal principal) {
		log.info("Hi man~!");
		log.info(ofs.getMessage().getProNo());
		System.out.println("권한 : " + principal);
		if (principal != null) {
			JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
			log.info("toString : " + token.getTokenAttributes().toString());
			model.addAttribute("list", token.getTokenAttributes());
		}

		service.bringLoan(Integer.parseInt(ofs.getMessage().getProNo()));

		model.addAttribute("productList", service.bringLoan(Integer.parseInt(ofs.getMessage().getProNo())));

		return "order";
	}

	@PostMapping("/save")
	@ResponseBody
	public void saveProduct(@RequestBody OrderOrderVO vo) {
		log.info("여기옴");
		service.saveOrder(vo);
	}

	// kafka producer
	@Autowired
	private final KafkaTemplate<String, SendMessage> kafkaTemplate;

	@Value(value = "${kafka.topic_name}")
	private String kafkaTopicName;

	@Value(value = "${kafka.server_endpoint}")
	private String kafkendPoinst;

	String status = "";

	@RequestMapping(value = "/kafka", method = RequestMethod.POST)
	public ResponseEntity<String> sendMessage(@RequestBody SendMessage message) {
		log.info("----------------------main/kafka/ URL 작동-----------------------");
		log.info("메세지 전동 된다. {}", message);

		ListenableFuture<SendResult<String, SendMessage>> future = this.kafkaTemplate.send(kafkaTopicName, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, SendMessage>>() {

			@Override
			public void onSuccess(SendResult<String, SendMessage> result) {
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