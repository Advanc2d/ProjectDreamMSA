package com.dream.confirm.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dream.confirm.dto.confirmDto;
import com.dream.confirm.service.confirmService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


//kafka 연동 말고 토큰으로 불러오는거만 되어있음;
//-------------------------------------------------------------------------------------------------
@Controller
@AllArgsConstructor
@Slf4j
public class confirmController {
	
	private confirmService service;
	

	@RolesAllowed({ "USER" })
	@GetMapping("/list")
	public String detail(Principal principal, Model model) {
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		String userId = (String) (token).getTokenAttributes().get("preferred_username");
	
		service.list(userId);
		model.addAttribute("keycloakList", token.getTokenAttributes());
		model.addAttribute("myList", service.list(userId));
		return "confirm-service-list";
	}
	
//	//manage/list 퍼옴
//	@RolesAllowed({ "user" })
//	@GetMapping("/list")
//	public String list(Model model, Principal principal) throws Exception {
//		log.info("---------------------- manage/list URL로 이동  -----------------------");
//		List<StatusDto> list = statusService.getStatusList();
//		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
//		model.addAttribute("list", token.getTokenAttributes());
//		
//		model.addAttribute("manageList", list);
//		return "manage-service-list";
//	}
	
	@RolesAllowed({ "USER" })
	@GetMapping("/check")
	public String check(Principal principal, Model model) {
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		String userId = (String) (token).getTokenAttributes().get("preferred_username");
//		if(service.list(userId)!=null){
		log.info("service : "+service.last(userId).toString());
		model.addAttribute("last", service.last(userId));
		model.addAttribute("keycloakList", token.getTokenAttributes());
		return "0408confirm-service-check";
//		}
//		//DB에 아무런 내용이 없을 경우
//		else {		
//			return "confirm-service-check-null";
//		}
	}	
}
