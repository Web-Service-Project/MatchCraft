package com.webservice.MatchCraft.webservice;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.MatchCraft.model.User;
import com.webservice.MatchCraft.serviceimp.MatchServiceImp;
import com.webservice.MatchCraft.serviceimp.UserServiceImp;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api")
public class WebserviceController {
	private final UserServiceImp userServiceImp;
	private final MatchServiceImp matchServiceImp;

	
	
	public WebserviceController(UserServiceImp userServiceImp, MatchServiceImp matchServiceImp) {
		super();
		this.userServiceImp = userServiceImp;
		this.matchServiceImp = matchServiceImp;
	}


	@Transactional
	@GetMapping("/allMatches")
	public List<User> listMatchs() {
		return matchServiceImp.getProductsFromStoredProcedure();
	}
}
