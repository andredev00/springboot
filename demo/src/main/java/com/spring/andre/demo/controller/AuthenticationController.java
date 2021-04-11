package com.spring.andre.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.andre.demo.dto.ClientDTO;
import com.spring.andre.demo.model.Client;
import com.spring.andre.demo.service.UserService;


@RestController
@RequestMapping(value = "/sign-up")
public class AuthenticationController {

	@Autowired
	UserService userService;
	
	@PostMapping(value = "/client")
	public Client registerClient(ClientDTO clientDTO) {
		
		return userService.registerClient(clientDTO);
		
	}
}
