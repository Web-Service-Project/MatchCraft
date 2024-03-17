package com.webservice.MatchCraft.serviceimp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webservice.MatchCraft.model.User;
import com.webservice.MatchCraft.repo.UserRepo;

import jakarta.persistence.EntityManager;

@Service
public class MatchServiceImp {
	
    private EntityManager entityManager;
	
	private final UserRepo userRepo;


    public MatchServiceImp(EntityManager entityManager, UserRepo userRepo) {
		super();
		this.entityManager = entityManager;
		this.userRepo = userRepo;
	}


	public List<User> getProductsFromStoredProcedure() {
        //StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("GetResults");
        //query.execute();
        return userRepo.executeStoredProcedure();//query.getResultList();
    }
}
