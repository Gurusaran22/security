package com.example.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.entity.User;
import com.example.security.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/register")
public class UserController {
     
	@Autowired
	private UserService userservice;
	@PostMapping("/")
	public User addUser(@RequestBody User user) {
	  return userservice.register(user);
	}
	
	@PostMapping("/login")
	public String checkUser(@RequestBody User user) {
		return userservice.verify(user);
	}
	
	@GetMapping("/all")
	public List<User> getMethodName() {
		return userservice.allData();
	}
	 
	
}
