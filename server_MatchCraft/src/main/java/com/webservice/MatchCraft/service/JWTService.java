package com.webservice.MatchCraft.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JWTService {

	String extractUserName(String token);
	String generateToken(UserDetails userDetails);
	boolean isTokenValid(String token, UserDetails userDetails);
	String generateFreshToken(java.util.Map<String, Object> extraClaims, UserDetails userDetails);
}
