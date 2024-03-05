package com.webservice.MatchCraft.webservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.MatchCraft.controller.dto.JwtAuthenticationResponse;
import com.webservice.MatchCraft.controller.dto.SignUpRequest;
import com.webservice.MatchCraft.controller.dto.SigninRequest;
import com.webservice.MatchCraft.model.User;
import com.webservice.MatchCraft.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationWebService {
	
	private final AuthenticationService authenticationService;
	
	
	public AuthenticationWebService(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}


	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
		return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest){
		return ResponseEntity.ok(authenticationService.signin(signinRequest));
	}
}
