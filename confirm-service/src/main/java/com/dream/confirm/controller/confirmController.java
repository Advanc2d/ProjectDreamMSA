package com.dream.confirm.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dream.confirm.dto.confirmDto;
import com.dream.confirm.service.ConfirmKafkaService;
import com.dream.confirm.service.confirmService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//kafka 연동 말고 토큰으로 불러오는거만 되어있음;
//-------------------------------------------------------------------------------------------------
@Controller
@Slf4j
public class confirmController {
	private final ConfirmKafkaService cks;
	private final confirmService service;

	@RolesAllowed({ "USER" })
	@GetMapping("/check")
	public String check(Principal principal, Model model) {
		log.info("여기 오고");
		if (principal != null) {
			JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
			log.info("toString : " + token.getTokenAttributes().toString());
			model.addAttribute("list", token.getTokenAttributes());
		}
		log.info(cks.getMessage().getUserId() + "---------------------");
		service.check(cks.getMessage().getUserId());
		
		model.addAttribute("User", service.check(cks.getMessage().getUserId()));
		
		
		return "confirm-service-check";
	}

	@RolesAllowed({"USER"})
	@GetMapping("/list")
	public String detail(Principal principal, Model model) {
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		String userId = (String) (token).getTokenAttributes().get("preferred_username");
	
		List<confirmDto> dto = null;
		dto = service.list(userId);
		model.addAttribute("list", token.getTokenAttributes());
		model.addAttribute("myList", service.list(userId));
		return "confirm-service-list";
	}
}
