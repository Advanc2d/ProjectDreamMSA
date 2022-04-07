package com.dream.confirm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ConfirmServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfirmServiceApplication.class, args);
	}

}
