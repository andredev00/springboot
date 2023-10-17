package com.spring.imobiliaria.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.spring.imobiliaria.dto.InputDto;
import com.spring.imobiliaria.dto.UserDTO;
import com.spring.imobiliaria.model.LoginCredentials;
import com.spring.imobiliaria.model.User;
import com.spring.imobiliaria.service.impl.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	UserServiceImpl userService;

	@PostMapping(value = "/sign-up")
	public void signUp(@ModelAttribute UserDTO userDTO, @RequestPart("file") MultipartFile multiPartFile) {
		userService.createUser(userDTO, multiPartFile);
	}
	
	@PutMapping(value = "/edit")
	public User editUser(@ModelAttribute UserDTO userDTO, @RequestPart("file") MultipartFile file,
			@RequestParam String id) {
		return userService.editUser(userDTO, file, id); //TODO, this method is not working
	}

	@PutMapping(value = "/reset/pass")
	public void resetPassword(@RequestParam String password, @RequestParam String uuid) {
		userService.resetPassword(password, uuid);
	}

	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginCredentials body) {
		try {
			return userService.login(body);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", e);
		}
	}

	@GetMapping("/agents")
	public List<User> getAgents() {
		return userService.getAgents();
	}

	@GetMapping("/agent/{name}/{id}")
	public User agentDetails(@RequestParam String name, @RequestParam String id) {
		return userService.getAgentDetail(name, id);
	}

	@RequestMapping("/active/account/{uuid}")
	public void activeAccount(@PathVariable("uuid") String uuid) {
		userService.activeAccount(uuid);
	}
	
	@GetMapping("/distinct/users/counties")
	public List<InputDto> getDistinctUsers(){
		return userService.getDistinctCounty();
	}
	
	@GetMapping("/distinct/users/name")
	public List<InputDto> getDistinctName(){
		return userService.getDistinctName();
	}
	
	@GetMapping("/distinct/users/agentType")
	public List<InputDto> getDistinctAgentType(){
		return userService.getDistinctAgentType();
	}
}