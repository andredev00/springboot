package com.spring.imobiliaria.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.imobiliaria.dto.HomeDTO;
import com.spring.imobiliaria.model.Home;
import com.spring.imobiliaria.service.impl.HomeServiceImpl;

@RestController
public class HomeController {
	
	@Autowired
	HomeServiceImpl homeService;

    @PostMapping(value = "/register/home", consumes = {"multipart/form-data"})
    public ResponseEntity<HomeDTO> registerHome(@ModelAttribute HomeDTO homeDTO, String userId) throws IOException{
	   return homeService.registerHome(homeDTO, userId);
    }
	
//	@DeleteMapping(value = "/delete/{id}")
//	public void deleteHome(@RequestParam Long id) {
//		homeService.deleteHome(id);
//	}

	@GetMapping(value = "/allHomes")
	public Iterable<Home> getAllHomes() {
		return homeService.getAllHomes();
	}

	@GetMapping(value = "/findHome/")
	public ResponseEntity<Home> getHome(@RequestParam String id) {
		return homeService.getHome(id);
	} 
	
//	@PostMapping(value = "/update/home/{id}")
//	public void updateHome(@RequestParam String id, @RequestBody HomeDTO homeDTO) {
//		homeService.updateHome(id, homeDTO);
//	}
}
