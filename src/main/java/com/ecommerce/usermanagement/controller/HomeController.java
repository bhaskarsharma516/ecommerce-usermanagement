package com.ecommerce.usermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.extern.slf4j.Slf4j;

@Controller
@EnableWebMvc
@Slf4j
public class HomeController {
	
	
	@GetMapping("/")
	public String home() {
		return "login";
	}


}
