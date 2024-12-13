package com.example.security.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	private  String secret="5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	
	public boolean validateToken(String token,UserDetails userdetails) {
		return true;
	}

	public String generateToken(String username) {
		 Map<String,Object> claims=new HashMap<>();
		return createToken(claims,username);
	}

	private String createToken(Map<String, Object> claims,String username) {
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()+ 1000 * 60 * 30))
				.signWith(getSignKey())
				.compact();
	}

	public  SecretKey getSignKey() {
		byte[] keybytes=Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keybytes);
	}

	//to get the student name
	public  String extractUserName(String token) {
	return extractClaim(token,Claims::getSubject);
	//	return "hello";
	}
	
	 private  <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimResolver.apply(claims);
	    }

	    private  Claims extractAllClaims(String token) {
	        return Jwts.parser()
	                .verifyWith(getSignKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }

	    public boolean validateToken1(String token, UserDetails userDetails) {
	        final String userName = extractUserName(token);
	        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	    private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

} 