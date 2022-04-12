package com.dream.gatewayservice.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GatewayController {

	@GetMapping("/token")
	public String index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
		log.info("------------------------ get token ------------------------------");
		return authorizedClient.getAccessToken().getTokenValue();
	}
}