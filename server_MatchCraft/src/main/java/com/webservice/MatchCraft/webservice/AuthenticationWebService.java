package com.webservice.MatchCraft.webservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.MatchCraft.controller.dto.JwtAuthenticationResponse;
import com.webservice.MatchCraft.controller.dto.SignUpRequest;
import com.webservice.MatchCraft.controller.dto.SigninRequest;
import com.webservice.MatchCraft.repo.UserRepo;
import com.webservice.MatchCraft.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationWebService {
	
	private final AuthenticationService authenticationService;
	private final UserRepo userRepo;
	
	public AuthenticationWebService(AuthenticationService authenticationService, UserRepo userRepo) {
		this.authenticationService = authenticationService;
		this.userRepo = userRepo;
	}


	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest){
		if(userRepo.existsByUserEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>("userEmailId is exist", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest){
		return ResponseEntity.ok(authenticationService.signin(signinRequest));
	}
}
