package com.dream.approveservice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public void update(@RequestBody orderVO vo) {
		log.info("---------------------- approve/change/ URL  DB UPDATE -----------------------");		
		service.update(vo);
		log.info("---------------------- approve/change/ URL  DB UPDATE COMPLETE-----------------------");	
	}
	
	@RolesAllowed({ "MANAGER" })
	@GetMapping("/detail")
	public String sendMsg(Model model, JwtAuthenticationToken principal) throws JsonMappingException, JsonProcessingException, ParseException {
		log.info("---------------------- approve/detail/ URL  URL MOVE -----------------------");
		JwtAuthenticationToken token =  principal;
		String userId = (String) (token).getTokenAttributes().get("preferred_username");
		log.info(userId);
		model.addAttribute("list", token.getTokenAttributes());
		
		log.info(serv.getMessage().getOrderNo());
		
		orderVO vo = new orderVO();
		vo = service.bringOrder(Integer.parseInt(serv.getMessage().getOrderNo()));
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date order = formatter.parse(vo.getOrderDate());
		Date end = formatter.parse(vo.getEndDate());
		
		String orderDate = formatter.format(order);
		String endDate = formatter.format(end);
		
		vo.setOrderDate(orderDate);
		vo.setEndDate(endDate);
		
		model.addAttribute("orderVO", vo);
		
		log.info("---------------------- approve/detail/Controller COMPLETE -----------------------");
		return "approve-service-detail";
	}
	
}
