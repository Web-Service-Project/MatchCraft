package com.webservice.MatchCraft.serviceimp;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.webservice.MatchCraft.model.User;
import com.webservice.MatchCraft.repo.UserRepo;
import com.webservice.MatchCraft.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImp implements UserService {
	private final UserRepo userRepo;

	public UserServiceImp(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public List<User> getAllMatchs() {
		return userRepo.findAll();
	}

	@Transactional
	@Override
	public List<User> callStoreProced() {
		// TODO Auto-generated method stub
		return userRepo.executeStoredProcedure();
	}

	@Override
	public UserDetailsService userDetailService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) {
				return userRepo.findByUserEmail(username)
						.orElseThrow(() -> new UsernameNotFoundException("User not found"));
			}
		};
	}
}