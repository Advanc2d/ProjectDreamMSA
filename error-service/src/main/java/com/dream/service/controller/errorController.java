package com.dream.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class errorController {

	@GetMapping("/failure")
	public String error() {
		return "fallback";
	}
}
