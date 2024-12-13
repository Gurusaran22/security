package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService UserDetailsService;
	
	 @Autowired
	 private JWTFilter twtfilter;
	@Bean
	SecurityFilterChain  securitychain(HttpSecurity httpsecurity) throws Exception{
		
		 	 httpsecurity.csrf(customizer-> customizer.disable());
		 	 httpsecurity.authorizeHttpRequests(request->request.requestMatchers("/api/register/login").permitAll());
		 	 httpsecurity.authorizeHttpRequests(request->request.anyRequest().authenticated());
		 	 httpsecurity.formLogin(Customizer.withDefaults());
		     httpsecurity.httpBasic(Customizer.withDefaults());
		     httpsecurity.addFilterBefore(twtfilter, UsernamePasswordAuthenticationFilter.class);
		 	 return httpsecurity.build();
				
	}
	
	@Bean
	 AuthenticationProvider authenticationprovider() {
		 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		 provider.setPasswordEncoder(new BCryptPasswordEncoder());
		 provider.setUserDetailsService(UserDetailsService);
		
		return provider;
	}
	//NoOpPasswordEncoder.getInstance()
	//new BCryptPasswordEncoder()

	@Bean
	 AuthenticationManager authenticationmanager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
