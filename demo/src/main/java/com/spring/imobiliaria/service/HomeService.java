package com.spring.imobiliaria.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.spring.imobiliaria.dto.HomeDTO;
import com.spring.imobiliaria.model.Home;

public interface HomeService {

	ResponseEntity<HomeDTO> registerHome(HomeDTO homeDTO, String userId) throws IOException;
	
	void deleteHome(Long id);
	
	Iterable<Home> getAllHomes();
	
	ResponseEntity<Home> getHome(String id);
	
	void updateHome(String id, HomeDTO homeDTO);
	
}
