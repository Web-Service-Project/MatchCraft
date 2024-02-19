package com.webservice.MatchCraft;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.webservice.MatchCraft.model.Role;
import com.webservice.MatchCraft.repo.RoleRepo;

@SpringBootApplication
public class ServerMatchCraftApplication {

	public static void main(String[] args) {
        SpringApplication.run(ServerMatchCraftApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner demo(RoleRepo roleRepo) {
        return (args) -> {
            Role role=new Role();
            role.setName("ROLE_ADMIN");
            roleRepo.save(role);
        };
    }
}
