package com.dream.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ErrorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErrorServiceApplication.class, args);
	}

}
