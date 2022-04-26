package com.dream.productservice.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dream.productservice.dto.ProductDto;
import com.dream.productservice.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@AllArgsConstructor
@Slf4j
@RefreshScope
public class ProductController {
	private final ProductService service;

	@RolesAllowed({ "ADMIN" })
	@GetMapping("/register")
	public String register(Principal principal, Model model) {
		log.info("---------------------- product/register URL Move -----------------------");
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		model.addAttribute("list", token.getTokenAttributes());
		
		log.info("---------------------- product/register URL Move Finish-----------------------");
		return "product-service-register";
	}

	
	@RolesAllowed({ "ADMIN" })
	@PostMapping(value = "/register", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	@ResponseBody
	public void register(@RequestBody ProductDto dto, Principal principal, Model model) {
		log.info("---------------------- product/register DB SAVE -----------------------");
		service.register(dto);
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		model.addAttribute("list", token.getTokenAttributes());	
		log.info("---------------------- product/register DB SAVE Success -----------------------");
	}
	
	
	@RolesAllowed({ "ADMIN" })
	@GetMapping("/list")
	public String dream(Principal principal, Model model) throws Exception {
		log.info("---------------------- product/list URL Print -----------------------");
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		model.addAttribute("list", token.getTokenAttributes());
		List<ProductDto> product = service.getProductList();
		model.addAttribute("dream", product);
		log.info("---------------------- product/list URL Print Finish -----------------------");
		return "product-service-list";
	}
	
	
	@RolesAllowed({ "ADMIN" })
	@GetMapping("/modify")
	public String modify(@RequestParam int proNo, Principal principal, Model model) {
		log.info("---------------------- product/modify view URL MOVE -----------------------");
		JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
		model.addAttribute("list", token.getTokenAttributes());
		model.addAttribute("proVO", service.getProduct(proNo));
		
		log.info("---------------------- product/modify view URL Finish -----------------------");
		return "product-service-modify";
	}
	
	
//	@RolesAllowed({ "ADMIN" })
//	@PostMapping("/modify")
//	@ResponseBody
//	public void update(@RequestBody ProductDto dto, Model model, Principal principal) {
//		log.info("---------------------- Controller Change product/modify DB modify -----------------------");
//		if (principal != null) {
//			JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
//			model.addAttribute("list", token.getTokenAttributes());
//		}
//		service.update(dto);		
//		log.info("---------------------- Controller Change product/modify DB modify Success -----------------------");
//	}
	
	@RolesAllowed({ "ADMIN" })
	@PostMapping(value = "/modify", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	@ResponseBody
	public void updateProduct(@RequestBody ProductDto dto, Model model, Principal principal) {
		log.info("---------------------- product/modify DB modify -----------------------");
		if (principal != null) {
			JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
			model.addAttribute("list", token.getTokenAttributes());
		}
		service.update(dto);		
		log.info("---------------------- product/modify DB modify Success -----------------------");
	}
	
	
	
	@RolesAllowed({ "ADMIN" })
	@PostMapping("/delete")
	public String delete(Principal principal, Model model, @RequestParam int proNo) {
		log.info("---------------------- product/delete  -----------------------");
		if (principal != null) {
			JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
			model.addAttribute("list", token.getTokenAttributes());
		}	
		service.delete(proNo);		
		log.info("---------------------- product/delete Success -----------------------");
		return "redirect:http://localhost:8000/product/list";
	}
	
}
