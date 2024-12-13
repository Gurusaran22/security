package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.security.entity.User;
import com.example.security.entity.myuser;
import com.example.security.repository.userRepository;

@Service
public class MyUserDetailsService implements  UserDetailsService{

 
	@Autowired
	private userRepository userrepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	      User users=userrepository.findByUsername(username);
		if(users==null) {
			System.out.println("user not found");
		}
		return new myuser(users);
	
	}

}