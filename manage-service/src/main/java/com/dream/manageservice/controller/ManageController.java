package com.dream.manageservice.controller;

import java.security.Principal;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.manageservice.dto.StatusDto;
import com.dream.manageservice.dto.StatusMessage;
import com.dream.manageservice.service.ManageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ManageController {
   private final ManageService statusService;

   @RolesAllowed({ "MANAGER" })
   @GetMapping("/list")
   public String list(Model model, Principal principal) throws Exception {
      log.info("---------------------- manage/list URL Move  -----------------------");
      List<StatusDto> list = statusService.getStatusList();
      
      JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
      model.addAttribute("list", token.getTokenAttributes());
      model.addAttribute("manageList", list);
      
      log.info("---------------------- manage/list URL Finish  -----------------------");
      return "manage-service-list";
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
      log.info("----------------------manage/kafka/ URL -----------------------");
      log.info("Manage Kafka Producer : {}", smsg);

      ListenableFuture<SendResult<String, StatusMessage>> future = this.kafkaTemplate.send(kafkaTopicName, smsg);

      future.addCallback(new ListenableFutureCallback<SendResult<String, StatusMessage>>() {

         @Override
         public void onSuccess(SendResult<String, StatusMessage> result) {
            status = "Message send successfully, 메시지가 성공적으로 전송 됨.";
            log.info("successfully sent message = {}, with offset = {}", smsg,
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