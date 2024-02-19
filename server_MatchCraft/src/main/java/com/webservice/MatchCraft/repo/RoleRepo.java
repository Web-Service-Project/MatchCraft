package com.webservice.MatchCraft.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservice.MatchCraft.model.Role;
public interface RoleRepo extends JpaRepository<Role, Integer> {
Optional<Role> findByName(String name);
}
