package com.spring.andre.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.andre.demo.dto.UserDTO;
import com.spring.andre.demo.model.User;
import com.spring.andre.demo.repository.UserRepository;

@Component
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User registerUser(UserDTO userDTO) {
		
		User user = new User();
		
		user.setName(userDTO.getName());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		
		return userRepository.save(user);	
	}
}
