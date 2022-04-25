package com.spring.andre.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.andre.demo.dto.UserDTO;
import com.spring.andre.demo.model.User;
import com.spring.andre.demo.repository.UserRepository;

@Component
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;
	
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public User registerUser(UserDTO userDTO) {
		log.info("Creating admin user with credentials: " + userDTO.getName() + " " + userDTO.getEmail() + " " + userDTO.getPassword());
		
		User user = new User(userDTO.getName(), userDTO.getEmail(), passwordEncoder().encode(userDTO.getPassword()));
		
		log.info("Finished creating admin user with credentials: " + userDTO.getName() + " " + userDTO.getEmail() + " " + userDTO.getPassword());
		return userRepository.save(user);	
	}
}
