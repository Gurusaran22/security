package com.example.security.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.entity.User;
import com.example.security.repository.userRepository;

@Service
public class UserService {

	@Autowired
	private userRepository userrepository;
	
	@Autowired
	private JWTService jwtservice;
	@Autowired
	private AuthenticationManager auth;
	
	 private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	 
	public User register(User user) {
		user.setPasssword(encoder.encode(user.getPasssword()));
		return userrepository.save(user);  
	}
    
	public List<User> allData(){
		return userrepository.findAll();
	}
	 //to verify the user ins there r not
	public String verify(User user) {
		Authentication authentication=auth.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPasssword()));
		
		if(authentication.isAuthenticated()) {
			return jwtservice.generateToken(user.getUsername());
		}
		else {return "login errr"; }
	}

}
