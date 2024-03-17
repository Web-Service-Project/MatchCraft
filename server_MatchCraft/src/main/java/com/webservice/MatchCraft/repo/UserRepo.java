package com.webservice.MatchCraft.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.webservice.MatchCraft.model.Role;
import com.webservice.MatchCraft.model.User;

public interface UserRepo extends JpaRepository<User, Long>{
	@Procedure(value = "GetResults")
    List<User> executeStoredProcedure();
	
	User findByUserEmail(String userEmail);
	User findByRole(Role role);
	Boolean existsByUserEmail(String userEmail);
}