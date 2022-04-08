package com.dream.orderService.controller;

import java.security.Principal;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dream.orderService.domain.Message;
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
}