package com.spring.andre.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.andre.demo.dto.ClientDTO;
import com.spring.andre.demo.dto.UserDTO;
import com.spring.andre.demo.model.Client;
import com.spring.andre.demo.model.User;
import com.spring.andre.demo.service.ClientService;
import com.spring.andre.demo.service.UserService;


@RestController
@RequestMapping(value = "/sign-up")
public class AuthenticationController {

	@Autowired
	ClientService clientService;
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/client")
	public Client registerClient(@RequestBody ClientDTO clientDTO) {
	    return clientService.registerClient(clientDTO);
	}
	
	@PostMapping(value = "/user")
	public User registerUser(@RequestBody UserDTO userDTO) {
		return userService.registerUser(userDTO);
	}
	
	@GetMapping(value = "/helloWorld")
	public String helloWorld() {
	    return "Hello World";
	}

}