package com.dream.mainservice.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.dream.mainservice.dto.Message;
import com.dream.mainservice.service.MainService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RefreshScope
public class MainController {
	private final MainService service;

	// 로그인 하지 않았을 시 return
//	@GetMapping("/")
//	public String main(Model model) throws Exception {
//		log.info("--------------------- No Login Main menu URL ------------------------------");
//		System.out.println(service.getProductList());
//		model.addAttribute("product", service.getProductList());
//		log.info("--------------------- No Login Main menu finish------------------------------");
//		return "main2";
//	}
	
	@GetMapping("/")
	public String main(Model model, @RequestParam(value = "delay", defaultValue = "0") int delay) throws Exception {
		log.info("--------------------- No Login Main menu URL ------------------------------");
		if(delay>0) {
			Thread.sleep(delay);	
		}
		System.out.println(service.getProductList());
		model.addAttribute("product", service.getProductList());
		log.info("--------------------- No Login Main menu finish------------------------------");
		return "main2";
	}
	
	// 로그인 되어있을 시 return
	@GetMapping("/menu")
	public String menu(Principal principal, Model model) throws Exception {
		log.info("--------------------- Login Main menu URL ------------------------------");
		System.out.println(service.getProductList());
		System.out.println("권한 : " + principal);
		if (principal != null) {
			JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
			log.info("toString : " + token.getTokenAttributes().toString());
			model.addAttribute("list", token.getTokenAttributes());
			model.addAttribute("product", service.getProductList());
		}
		
		log.info("--------------------- Login Main menu finish------------------------------");
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
		log.info("Main Kafka Producer : {}", message);

		ListenableFuture<SendResult<String, Message>> future = this.kafkaTemplate.send(kafkaTopicName, message);
		future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {

			@Override
			public void onSuccess(SendResult<String, Message> result) {
				status = "Message send successfully, 메시지가 성공적으로 전송 됨.";
				log.info("successfully sent message = {}, with offset = {}", message,
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

	@GetMapping("/test")
	public void authlogin(HttpServletRequest request, Principal principal, Model model) throws Exception {
//	        throw new RuntimeException("failed");
		log.info("--------------------- main/test URL CircuitBreaker Error Page Move ------------------------------");
		try {
			Thread.sleep(11000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("server port : " + request.getServerPort() + "\nserver:name : " + request.getServerName());
		log.info("---------------------  main/test URL Log Finish ------------------------------");
		// 리턴값 변경 test
	}

}