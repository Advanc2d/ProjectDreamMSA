package com.dream.confirm.controller;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dream.confirm.service.ConfirmKafkaService;
import com.dream.confirm.service.confirmService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class confirmController {
	private final ConfirmKafkaService cks;
	private final confirmService service;
	
	@RolesAllowed({ "USER" })
	@GetMapping("/list")
	public String detail(Principal principal, Model model) {
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		String userId = (String) (token).getTokenAttributes().get("preferred_username");
		service.list(userId);
		model.addAttribute("list", service.list(userId));
		model.addAttribute("keycloakList", token.getTokenAttributes());
		return "detail";
	}
	
	@GetMapping("/check")
	public String check(Principal principal, Model model) {
		log.info("여기 오고");
		if (principal != null) {
			JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
			log.info("toString : " + token.getTokenAttributes().toString());
			model.addAttribute("list", token.getTokenAttributes());
		}
		service.list(cks.getMessage().getUserId());
		
		model.addAttribute("User",service.list(cks.getMessage().getUserId()));
		
		return "check";
	}
}