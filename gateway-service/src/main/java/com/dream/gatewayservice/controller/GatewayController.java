package com.dream.gatewayservice.controller;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GatewayController {

	@GetMapping("/token")
	public String index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
		return authorizedClient.getAccessToken().getTokenValue();
	}

//	@RequestMapping("/fallback/Faliure")
//	public String testFallback(ServerHttpRequest request, ServerHttpResponse response) {
//		log.info("fallback ..............."+response.toString());
//		return "해당 서비스에 문제가 발생하여 현재 이용이 어렵습니다. 잠시 후 다시 이용해주시길 바랍니다.";
//	}

	@GetMapping("/test")
	public String test() {
		return "test";
	}

}