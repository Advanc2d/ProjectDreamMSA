package com.dream.productservice.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dream.productservice.dto.ProductDto;
import com.dream.productservice.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@AllArgsConstructor
@Slf4j
public class ProductController {
	private final ProductService service;

	@RolesAllowed({ "ADMIN" })
	@GetMapping("/register")
	public String register(Principal principal, Model model) {
		log.info("---------------------- product/register URL �̵� -----------------------");
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		log.info("toString : " + token.getTokenAttributes().toString());
		model.addAttribute("list", token.getTokenAttributes());
		return "product-service-register";
	}

	
	@RolesAllowed({ "ADMIN" })
	@PostMapping(value = "/register", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	@ResponseBody
	public void register(@RequestBody ProductDto dto, Principal principal, Model model) {
		log.info("---------------------- product/register DB저장하러 왔다. -----------------------");
		log.info(dto.toString());
		service.register(dto);
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		log.info("toString : " + token.getTokenAttributes().toString());
		model.addAttribute("list", token.getTokenAttributes());
		log.info("dto.toString() : " + dto.toString());
		/* return "regist_success"; */
	}
	
	
	@RolesAllowed({ "ADMIN" })
	@GetMapping("/list")
	public String dream(Principal principal, Model model) throws Exception {
		log.info("---------------------- product/list URL �̵� -----------------------");
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		log.info("toString : " + token.getTokenAttributes().toString());
		model.addAttribute("list", token.getTokenAttributes());
		List<ProductDto> product = service.getProductList();
		model.addAttribute("dream", product);
		return "product-service-list";
	}
	
	
	@RolesAllowed({ "ADMIN" })
	@GetMapping("/modify")
	public String modify(@RequestParam int proNo, Principal principal, Model model) {
		log.info("---------------------- product/modify URL  �̵� -----------------------");;
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		log.info("toString : " + token.getTokenAttributes().toString());
		model.addAttribute("list", token.getTokenAttributes());
		model.addAttribute("proVO", service.getProduct(proNo));
		
		return "modify3";
	}
	
	
	@RolesAllowed({ "ADMIN" })
	@PostMapping(value = "/modify", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String updateProduct(@RequestBody ProductDto dto) {
		log.info("---------------------- product/modify DB수정 �����Ϸ� �̵� -----------------------");
		service.update(dto);
		return "updateProduct"; 
	}
	
	
	@RolesAllowed({ "ADMIN" })
	@PostMapping("/delete")
	public String delete(@RequestParam int proNo) {
		log.info("---------------------- product/delete URL �����Ϸ� �̵� -----------------------");
		service.delete(proNo);
		return "redirect:http://localhost:8000/product/list";
	}
	
	@RequestMapping("/test")
	   public String authlogin(HttpServletRequest request) {
//	        throw new RuntimeException("failed");
	      try {
	         Thread.sleep(50000);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      log.info("request server port : {}", request.getServerPort());
	      return "test"; // 리턴값 변경 test
	   }
}
