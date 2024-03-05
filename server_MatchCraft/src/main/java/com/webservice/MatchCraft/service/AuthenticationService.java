package com.webservice.MatchCraft.service;

import com.webservice.MatchCraft.model.User;
import com.webservice.MatchCraft.controller.dto.JwtAuthenticationResponse;
import com.webservice.MatchCraft.controller.dto.SignUpRequest;
import com.webservice.MatchCraft.controller.dto.SigninRequest;

public interface AuthenticationService {
	User signUp(SignUpRequest signUpRequest);
	JwtAuthenticationResponse signin(SigninRequest signinRequest);
}
