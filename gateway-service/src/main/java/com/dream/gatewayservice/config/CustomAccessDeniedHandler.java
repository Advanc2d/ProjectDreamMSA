package com.dream.gatewayservice.config;

import java.nio.charset.StandardCharsets;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class CustomAccessDeniedHandler implements ServerAccessDeniedHandler {

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
		ServerHttpResponse response = exchange.getResponse();
		System.out.println("넘어오니");
		response.setStatusCode(HttpStatus.FORBIDDEN);
		response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		String responseBody = "{\"error\": \"" + denied.getLocalizedMessage() + "입니다\"}";
		byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);
		DataBuffer buffer = response.bufferFactory().wrap(bytes);
		return response.writeWith(Mono.just(buffer));

	}
}
