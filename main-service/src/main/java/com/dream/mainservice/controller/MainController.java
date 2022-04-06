package com.dream.mainservice.controller;

import java.security.Principal;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dream.mainservice.service.MainService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
	private final MainService service;

	// 로그인 하지 않았을 시 return
	@GetMapping("/")
	public String main(Model model) throws Exception {
		log.info("come on man~");
		System.out.println(service.getProductList());
		model.addAttribute("product", service.getProductList());
		return "main2";
	}

	// 로그인 되어있을 시 return
	@GetMapping("/menu")
	public String menu(Principal principal, Model model) throws Exception {
		log.info("Hi man~!");
		System.out.println(service.getProductList());
		System.out.println("권한 : " + principal);
		if (principal != null) {
			JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
			log.info("toString : " + token.getTokenAttributes().toString());
			model.addAttribute("list", token.getTokenAttributes());
			model.addAttribute("product", service.getProductList());
		}
		return "main";
	}
}