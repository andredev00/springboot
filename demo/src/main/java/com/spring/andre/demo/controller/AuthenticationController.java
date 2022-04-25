package com.spring.andre.demo.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.andre.demo.dto.ClientDTO;
import com.spring.andre.demo.dto.UserDTO;
import com.spring.andre.demo.model.LoginCredentials;
import com.spring.andre.demo.model.User;
import com.spring.andre.demo.security.JWTUtil;
import com.spring.andre.demo.service.ClientService;
import com.spring.andre.demo.service.UserService;


@RestController
@RequestMapping(value = "/sign-up")
public class AuthenticationController {

	@Autowired
	ClientService clientService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping(value = "/client")
	public Map<String, String> registerClient(@RequestBody ClientDTO clientDTO) {
	    return clientService.registerClient(clientDTO);
	}
	
	@PostMapping(value = "/user")
	public User registerUser(@RequestBody UserDTO userDTO) {
		return userService.registerUser(userDTO);
	}
	
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginCredentials body){
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