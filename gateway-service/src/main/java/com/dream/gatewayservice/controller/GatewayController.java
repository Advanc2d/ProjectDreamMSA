package com.dream.gatewayservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RefreshScope
public class GatewayController {

	@GetMapping("/token")
	public String index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
		log.info("------------------------ get token ------------------------------");
		return authorizedClient.getAccessToken().getTokenValue();
	}
	
	@Value("${config.test}")
	private String str;
	   
	@Value("${server.port}")
	private String port;
	
	@GetMapping("/config")
	public String test() {
		   log.info("-------------------- Config Test ---------------------");
		   log.info("--------"+ str +"--------");
		   log.info("-------------------- Config Test Finish---------------------");
		   return str + " / 내부 포트 번호 : " + port;
	}
}