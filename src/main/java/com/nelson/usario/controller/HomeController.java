package com.nelson.usario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@Controller
public class HomeController {

	@GetMapping("/")
	public String inicio() {
		return "templates/inicio";
	}
}
