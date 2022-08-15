package com.spring.andre.demo.controller;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.andre.demo.dto.UserDTO;
import com.spring.andre.demo.model.LoginCredentials;
import com.spring.andre.demo.model.User;
import com.spring.andre.demo.service.UserService;


@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/sign-up/client")
	public User registerClient(@RequestBody UserDTO userDTO) throws MessagingException {
		return userService.registerClient(userDTO);
	}

	@PostMapping(value = "/sign-up/user")
	public User registerUser(@RequestBody UserDTO userDTO) throws MessagingException {
		return userService.registerUser(userDTO);
	}
	
	@PutMapping(value = "/edit")
	public User editUser(@ModelAttribute UserDTO userDTO, @RequestPart("file") MultipartFile file, @RequestParam String id) {
		return userService.editUser(userDTO, file, id);
	}
	
	@PutMapping(value = "/reset/pass")
	public void resetPassword(@RequestParam String password, @RequestParam String uuid) {
		 userService.resetPassword(password, uuid);
	}

	// generic login for user and client
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginCredentials body) {
		return userService.login(body);
	}
	
	@GetMapping("/agents")
	public List<User> getAgents() {
		return userService.getAgents();
	}
	
	@PostMapping("/active/account/{uuid}")
	public void activeAccount(@PathVariable("uuid") String uuid) {
		userService.activeAccount(uuid);
	}
}