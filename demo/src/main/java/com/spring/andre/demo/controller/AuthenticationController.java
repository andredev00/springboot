package com.spring.andre.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.andre.demo.dto.UserDTO;
import com.spring.andre.demo.model.LoginCredentials;
import com.spring.andre.demo.model.User;
import com.spring.andre.demo.service.UserService;

@RestController
@RequestMapping(value = "/sign-up")
public class AuthenticationController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/client")
	public User registerClient(@RequestBody UserDTO userDTO) {
		return userService.registerClient(userDTO);
	}

	@PostMapping(value = "/user")
	public User registerUser(@RequestBody UserDTO userDTO) {
		return userService.registerUser(userDTO);
	}
	
	@PutMapping(value = "/edit")
	public User editUser(@RequestBody UserDTO userDTO) {
		return userService.editUser(userDTO);
	}
	
	@PutMapping(value = "/reset/pass")
	public User resetPassword(@RequestBody UserDTO userDTO) {
		return userService.resetPassword(userDTO);
	}

	// generic login for user and client
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginCredentials body) {
		return userService.login(body);
	}
}