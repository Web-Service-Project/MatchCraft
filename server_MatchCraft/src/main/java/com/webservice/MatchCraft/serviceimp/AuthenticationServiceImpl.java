package com.webservice.MatchCraft.serviceimp;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webservice.MatchCraft.model.Role;
import com.webservice.MatchCraft.model.User;
import com.webservice.MatchCraft.repo.UserRepo;
import com.webservice.MatchCraft.service.AuthenticationService;
import com.webservice.MatchCraft.service.JWTService;
import com.webservice.MatchCraft.controller.dto.JwtAuthenticationResponse;
import com.webservice.MatchCraft.controller.dto.SignUpRequest;
import com.webservice.MatchCraft.controller.dto.SigninRequest;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	 private final UserRepo userRepositroy;
	 private final PasswordEncoder passwordEncoder;
	 
	 private final AuthenticationManager authenticationManager;
	 
	 private final JWTService jwtService;
	 
	 


	public AuthenticationServiceImpl(UserRepo userRepositroy, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, JWTService jwtService) {
		super();
		this.userRepositroy = userRepositroy;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	public User signUp(SignUpRequest signUpRequest) {
		 User user = new User();
		 user.setUserEmail(signUpRequest.getEmail());
		 user.setUserName(signUpRequest.getUserName());
		 user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		 user.setSkillLevel(signUpRequest.getSkillLevel());
		 user.setGamePreferences(signUpRequest.getGamePreferences());
		 user.setRole(Role.USER);
		 
		 //var jwt = jwtService.generateToken(user);
		return userRepositroy.save(user);
	 }
	
	public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
		
		// Check if email or password is empty
	    if (signinRequest.getEmail() == null || signinRequest.getEmail().isEmpty() ||
	        signinRequest.getPassword() == null || signinRequest.getPassword().isEmpty()) {
	        throw new IllegalArgumentException("Email or password cannot be empty.");
	    }
		//authenticationManager will validate the email and password
		//it retrun the exception if the info dont match
	    try {
	    	
	        Authentication auth =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
	        SecurityContextHolder.getContext().setAuthentication(auth);
	        
	        //return new ResponseEntity<>("User sighned sucess", HttpStatus.OK);
	    } catch (Exception ex) {
	    	System.err.println("Error during authentication: " + ex.getMessage());
;
	    }
		
		
		//then we can fetch user from DB and we can use jwt service to generatea token
		var user = userRepositroy.findByUserEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
		
		//we can call our JWT service 
		//we need jwtservice
		var jwt = jwtService.generateToken(user);
		
		//after get jwt we need to refresh token
		//call a methis for refresh token
		var refreshToken = jwtService.generateFreshToken(new HashMap<>(), user);
		
		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		jwtAuthenticationResponse.setToken(jwt);
		jwtAuthenticationResponse.setRefreshToken(refreshToken);
		
		return jwtAuthenticationResponse;
	}
}
