package com.webservice.MatchCraft.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservice.MatchCraft.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	User findByUserNameOrEmail(String userName, String email);

boolean existsByUserName(String username);
boolean existsByEmail(String email);

}