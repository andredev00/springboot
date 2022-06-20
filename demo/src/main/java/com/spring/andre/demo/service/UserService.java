package com.spring.andre.demo.service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

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
	
	public User registerClient(UserDTO userDTO) {
		log.info("Creating a new client with credentials: " + userDTO.getName() + " " + userDTO.getEmail());
		
		Optional<User> userExists = userRepository.findByEmail(userDTO.getEmail());
		
		if(userExists.isEmpty()) {
			User user = new User(userDTO.getName(), userDTO.getEmail(), passwordEncoder().encode(userDTO.getPassword()),  userDTO.getAddress(), userDTO.getPhoneNumber(), userDTO.getDateBirth(), "USER");
			
			log.info("Finished creating a new client with credenials: " + " " + userDTO.getName() + " " + userDTO.getEmail());
			user = userRepository.save(user);
			return user;
		}
		
		return null;
	}
	
	public User registerUser(UserDTO userDTO) {
		log.info("Creating admin user with credentials: " + userDTO.getName() + " " + userDTO.getEmail() + " " + userDTO.getPassword());

		Optional<User> userExists = userRepository.findByEmail(userDTO.getEmail());
		
		if(userExists == null || userExists.isEmpty()) {
			User user = new User(userDTO.getName(), userDTO.getEmail(), passwordEncoder().encode(userDTO.getPassword()), userDTO.getAddress(), userDTO.getPhoneNumber(), userDTO.getDateBirth(), "ADMIN");
			
			log.info("Finished creating admin user with credentials: " + userDTO.getName() + " " + userDTO.getEmail() + " " + userDTO.getPassword());
			return userRepository.save(user);	
		}
		
		return null;
	}
	
	//TODO, this is here because all the authentication logic with be refactor, for using only one object/entity, avoiding repeating code
	public Map<String, Object> login(LoginCredentials body){
		try {
			String permissions = "";
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
			
			authenticationManager.authenticate(authInputToken);
			
			Optional<User> userExists = userRepository.findByEmail(body.getEmail());
			if(userExists.isPresent()) {
				permissions = userExists.get().getPermissions();	
			}
				
			String token = jwtUtil.generateToken(body.getEmail(), permissions);
			
			return Collections.singletonMap("jwt", token);
		} catch (Exception e) {
			throw new RuntimeException("Invalid Login Credentials");
		}
	}

	public User editUser(UserDTO userDTO) {
		log.info("Updating information for user: " + userDTO.getEmail());
		
		User user = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getAddress(), userDTO.getPhoneNumber(), userDTO.getDateBirth());
		
		log.info("Finished updating information for user: " + userDTO.getEmail());
		return userRepository.save(user);
	}

	public User resetPassword(UserDTO userDTO) {
		log.info("Updating password for user: " + userDTO.getEmail());
		
		User user = new User(userDTO.getName(), userDTO.getEmail(), passwordEncoder().encode(userDTO.getPassword()),  userDTO.getAddress(), userDTO.getPhoneNumber(), userDTO.getDateBirth(), userDTO.getPermissions());
		
		log.info("Finished updating password for user: " + userDTO.getEmail());
		return userRepository.save(user);
	}
	
}
