package com.webservice.MatchCraft.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.webservice.MatchCraft.model.User;

public interface UserService {
	List<User> getAllMatchs();
	//Credential registerUser(Credential user);
	//Register findByUsername(String username);
	
	List<User> callStoreProced();
	UserDetailsService userDetailService();
}
