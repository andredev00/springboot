package com.spring.andre.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.andre.demo.dto.HomeDTO;
import com.spring.andre.demo.model.Home;
import com.spring.andre.demo.service.HomeService;

@RestController
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	HomeService homeService;

	@PostMapping(value = "/register/home")
	public Home registerHome(@RequestBody HomeDTO homeDTO) {
		log.info("Inside the registerHome endpoint");
		return homeService.registerHome(homeDTO);
	}

	@DeleteMapping(value = "/delete/{id}")
	public void deleteHome(@RequestParam Long id) {
		log.info("Inside the deleteHome endpoint");
		homeService.deleteHome(id);
	}

	@GetMapping(value = "/allHomes")
	public Iterable<Home> getAllHomes() {
		log.info("Inside the getAllHomes endpoint");
		return homeService.getAllHomes();
	}

	@GetMapping(value = "/findHome/{id}")
	public void getHome(@RequestParam Long id) {
		log.info("Inside the getHome endpoint");
		homeService.getHome(id);
	}

}
