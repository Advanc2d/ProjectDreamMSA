package com.dream.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class errorController {

	@GetMapping("/failure")
	public String error() {
		log.info("-----------------error-service fialure fallback-------------------------");
		return "fallback";
	}
}
