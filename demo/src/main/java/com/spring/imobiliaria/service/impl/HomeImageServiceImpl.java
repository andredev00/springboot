package com.spring.imobiliaria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.imobiliaria.model.HomeImage;
import com.spring.imobiliaria.repository.HomeImageRepository;

@Component
public class HomeImageServiceImpl {

	@Autowired
	HomeImageRepository homeImageRepository;
	
	public List<HomeImage> fetchImage(String id) {
		return homeImageRepository.findByHomeId(id);
	}

}
