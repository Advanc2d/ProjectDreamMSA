package com.dream.orderService.controller;

import java.security.Principal;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
//@RequiredArgsConstructor
public class OrderServiceController {
	@GetMapping("/detail")
	public String loanPage(Model model, Principal principal) {
		log.info("Hi man~!");
		System.out.println("권한 : " + principal);
		if (principal != null) {
			JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
			log.info("toString : " + token.getTokenAttributes().toString());
			model.addAttribute("list", token.getTokenAttributes());
		}
		return "order";
	}
}