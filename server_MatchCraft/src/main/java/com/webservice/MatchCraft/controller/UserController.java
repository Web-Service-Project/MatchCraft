package com.webservice.MatchCraft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.webservice.MatchCraft.controller.dto.SignUpRequest;
import com.webservice.MatchCraft.controller.dto.SigninRequest;
import com.webservice.MatchCraft.service.AuthenticationService;
import com.webservice.MatchCraft.serviceimp.UserServiceImp;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	private final UserServiceImp userServiceImp;
	private final AuthenticationService authenticationService;

	
	public UserController(UserServiceImp userServiceImp, AuthenticationService authenticationService) {
		super();
		this.userServiceImp = userServiceImp;
		this.authenticationService = authenticationService;
	}

	// handler method to handle list students and return mode and view
	// "index" is Thymeleaf template for home page
//	@GetMapping("/")
//    public String homePage() {
//        return "index"; 
//    }
	
	@GetMapping("/register")
    public String registerPage() {
        return "register";
    }
	
	@PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("Register") SignUpRequest userCredInfo, BindingResult result) {       
        // Return to the registration page with error messages
        if (result.hasErrors()) {
            return "register"; 	
        }
        
        //edirect to login page after successful registration
        authenticationService.signUp(userCredInfo);
        return "redirect:/login";
    }
	
	@GetMapping("/login")
    public String loginPage(Model model) {
		SigninRequest userReg = new SigninRequest();
		model.addAttribute("userCredential", userReg);
        return "login";
    }
	
	@GetMapping("/allMatches")
	public String listMatchs(Model model) {
//		model.addAttribute("allMatches", userServiceImp.getAllMatchs());
		model.addAttribute("allMatches", userServiceImp.callStoreProced());
		return "allMatches";
	}
}
