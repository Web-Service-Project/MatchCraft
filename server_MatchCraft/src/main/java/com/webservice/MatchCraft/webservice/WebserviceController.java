package com.webservice.MatchCraft.webservice;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.MatchCraft.model.User;
import com.webservice.MatchCraft.serviceimp.MatchServiceImp;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api")
public class WebserviceController {
	private final MatchServiceImp matchServiceImp;

	
	
	public WebserviceController(MatchServiceImp matchServiceImp) {
		this.matchServiceImp = matchServiceImp;
	}


	@Transactional
	@GetMapping("/allMatches")
	public List<User> listMatchs() {
		return matchServiceImp.getProductsFromStoredProcedure();
	}
}
