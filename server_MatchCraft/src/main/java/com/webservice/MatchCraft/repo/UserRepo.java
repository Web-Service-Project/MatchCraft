package com.webservice.MatchCraft.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.webservice.MatchCraft.model.User;

public interface UserRepo extends CrudRepository<User, Long>{
	Optional<User> findByEmail(String email);
}
