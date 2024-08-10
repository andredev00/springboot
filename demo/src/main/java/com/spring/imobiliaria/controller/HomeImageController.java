package com.spring.imobiliaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.imobiliaria.model.HomeImage;
import com.spring.imobiliaria.service.impl.HomeImageServiceImpl;

@RestController
public class HomeImageController {
	
	@Autowired
	HomeImageServiceImpl homeImageServiceImpl;
	
	@GetMapping("/fetchImage")
	public List<HomeImage> fetchImage(@RequestParam String id) {
		return homeImageServiceImpl.fetchImage(id);
	}
	
}
