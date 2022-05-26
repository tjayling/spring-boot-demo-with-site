package com.qa.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Root {
	@GetMapping("/home")
	public String home() {
		return("/home.html");
	}
}
