package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class WelcomeController {

	
	 @GetMapping("/welcome")
	    public String welcome() {
	        return "Welcome this endpoint is not secure";
	    }
}
