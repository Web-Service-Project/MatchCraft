package com.webservice.MatchCraft.service;

import com.webservice.MatchCraft.controller.dto.JwtAuthenticationResponse;
import com.webservice.MatchCraft.controller.dto.SignUpRequest;
import com.webservice.MatchCraft.controller.dto.SigninRequest;

public interface AuthenticationService {
	String signUp(SignUpRequest signUpRequest);
	JwtAuthenticationResponse signin(SigninRequest signinRequest);
}
