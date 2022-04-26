package com.dream.manageservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RefreshScope
@Slf4j
public class ConfigTestController {
	
	   @Value("${manage.test}")
	   private String str;
	   
	   @GetMapping("/test")
	   public String test() {
		   log.info("-------------------- Config Test ---------------------");
		   log.info("--------"+ str +"--------");
		   log.info("-------------------- Config Test Finish---------------------");
		   return str;
	   }
}
