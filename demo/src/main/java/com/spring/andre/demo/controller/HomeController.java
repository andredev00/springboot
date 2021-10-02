package com.spring.andre.demo.controller;

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

    @Autowired
    HomeService homeService;
	
	@PostMapping(value = "/register/home")
    public Home registerHome(@RequestBody HomeDTO homeDTO){
	    return homeService.registerHome(homeDTO);
    }
	
	@DeleteMapping(value = "/delete/{id}")
	public void deleteHome(@RequestParam Long id) {
		 homeService.deleteHome(id);
	}
	
	@GetMapping(value = "/allHomes")
	public Iterable<Home> getAllHomes() {
		return homeService.getAllHomes();
	}
	
	@GetMapping(value = "/findHome/{id}")
	public void getHome(@RequestParam Long id) {
		 homeService.getHome(id);
	}
	
}
