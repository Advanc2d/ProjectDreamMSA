package com.dream.orderService.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dream.orderService.domain.OrderProductVO;
import com.dream.orderService.service.OrderService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
//@RequiredArgsConstructor
public class OrderServiceController {
	private final OrderService service;
	
	
//	@RolesAllowed({"USER"})
	@GetMapping("/detail")
	public String loanPage(Model model, Principal principal) throws Exception {
		log.info("---------------------- order-service/detail URL 로 이동 -----------------------");
		log.info("Hi man~!");
		System.out.println("권한 : " + principal);
		if (principal != null) {
			JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
			log.info("toString : " + token.getTokenAttributes().toString());
			model.addAttribute("list", token.getTokenAttributes());
		}
		
		List<OrderProductVO> product = service.getProductList();
		model.addAttribute("dream", product);
		
		return "order-service-detail";
	}
}