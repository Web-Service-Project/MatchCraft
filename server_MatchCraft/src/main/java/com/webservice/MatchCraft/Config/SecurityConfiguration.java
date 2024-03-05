package com.webservice.MatchCraft.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.webservice.MatchCraft.model.Role;
import com.webservice.MatchCraft.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	//we need to inject service of JWT authentication
	
	private final JWTAuthenticationFillter jwtAuthenticationFillter;
	private final UserService userService;
	
	//authentication controll path is /api/v1/auth/**
	// /api/v1/auth/**.permitAll() allow our API to be accessable   
	public SecurityConfiguration(JWTAuthenticationFillter jwtAuthenticationFillter, UserService userService) {
		super();
		this.jwtAuthenticationFillter = jwtAuthenticationFillter;
		this.userService = userService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(request -> request.requestMatchers("/api/v1/auth/**")
				.permitAll()
				.requestMatchers("/api/**").permitAll()
				.requestMatchers("/**").permitAll()
				.requestMatchers("/api/v1/admin").hasAnyAuthority(Role.ADMIN.name())
				.requestMatchers("/api/v1/user").hasAnyAuthority(Role.USER.name())
				.anyRequest().authenticated())
		
		.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthenticationFillter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService.userDetailService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) 
		throws Exception {
			return config.getAuthenticationManager();
		}
	
}
