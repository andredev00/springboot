package com.spring.imobiliaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.imobiliaria.dto.HomeDTO;
import com.spring.imobiliaria.model.Home;
import com.spring.imobiliaria.service.impl.HomeServiceImpl;

@RestController
public class HomeController {
	
	@Autowired
	HomeServiceImpl homeService;

	@PostMapping(value = "/register/home", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void registerHome(@ModelAttribute HomeDTO homeDTO, @RequestPart("file") MultipartFile file, String userId){
	    homeService.registerHome(homeDTO, file, userId);
    }
	
	@DeleteMapping(value = "/delete/{id}")
	public void deleteHome(@RequestParam Long id) {
		homeService.deleteHome(id);
	}

	@GetMapping(value = "/allHomes")
	public Iterable<Home> getAllHomes() {
		return homeService.getAllHomes();
	}

	@GetMapping(value = "/findHome/")
	public List<Home> getHome(@RequestParam String id) {
		return homeService.getHome(id);
	} 
	
	@PostMapping(value = "/update/home/{id}")
	public void updateHome(@RequestParam String id, @RequestBody HomeDTO homeDTO) {
		homeService.updateHome(id, homeDTO);
	}
}
