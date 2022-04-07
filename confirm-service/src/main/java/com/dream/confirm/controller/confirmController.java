package com.dream.confirm.controller;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dream.confirm.service.confirmService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class confirmController {
	
	private confirmService service;
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
}
