package com.spring.andre.demo.service;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.andre.demo.dto.UserDTO;
import com.spring.andre.demo.model.LoginCredentials;
import com.spring.andre.demo.model.User;
import com.spring.andre.demo.repository.UserRepository;
import com.spring.andre.demo.security.JWTUtil;

@Component
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public User registerUser(UserDTO userDTO) {
		log.info("Creating admin user with credentials: " + userDTO.getName() + " " + userDTO.getEmail() + " " + userDTO.getPassword());
		
		User user = new User(userDTO.getName(), userDTO.getEmail(), passwordEncoder().encode(userDTO.getPassword()));
		
		log.info("Finished creating admin user with credentials: " + userDTO.getName() + " " + userDTO.getEmail() + " " + userDTO.getPassword());
		return userRepository.save(user);	
	}
	
	public Map<String, Object> login(LoginCredentials body){
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
			
			authenticationManager.authenticate(authInputToken);
						
			String token = jwtUtil.generateToken(body.getEmail());
			
			return Collections.singletonMap("jwt-token", token);
		} catch (Exception e) {
			throw new RuntimeException("Invalid Login Credentials");
		}
	}
	
}
