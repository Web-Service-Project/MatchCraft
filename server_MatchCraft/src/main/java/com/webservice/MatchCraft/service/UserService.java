package com.webservice.MatchCraft.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.webservice.MatchCraft.model.User;

public interface UserService {
	List<User> getAllMatchs();
	
	List<User> callStoreProced();
	UserDetailsService userDetailService();
}
