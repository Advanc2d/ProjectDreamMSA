package com.dream.approveservice.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dream.approveservice.dto.orderVO;
import com.dream.approveservice.service.ApproveKafkaService;
import com.dream.approveservice.service.ApproveService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Controller
public class ApproveController {
	
	private final ApproveKafkaService serv;
	private final ApproveService service;
	
	@RolesAllowed({ "MANAGER" })
	@PostMapping("/change")
	public void update(@RequestBody orderVO vo) {
		log.info("---------------------- approve/change/ URL  DB������Ʈ �̵� -----------------------");
		log.info(vo.getOrderNo()+"�̰� �ȴٰ�??" + vo.getStatus());
		service.update(vo.getOrderNo(), vo.getStatus());
	}
	
	@RolesAllowed({ "MANAGER" })
	@GetMapping("/detail")
	public String sendMsg(Model model, JwtAuthenticationToken principal) throws JsonMappingException, JsonProcessingException {
		log.info("---------------------- approve/detail/ URL  �̵� -----------------------");
		JwtAuthenticationToken token =  principal;
		String userId = (String) (token).getTokenAttributes().get("preferred_username");
		log.info(userId);
		model.addAttribute("list", token.getTokenAttributes());
		
		log.info(serv.getMessage().getOrderNo());
		
		orderVO vo = new orderVO();
		vo = service.bringOrder(Integer.parseInt(serv.getMessage().getOrderNo()));
		
		log.info(vo.toString());
		
		model.addAttribute("orderVO", service.bringOrder(Integer.parseInt(serv.getMessage().getOrderNo())));
		return "detail";
	}
	
}
