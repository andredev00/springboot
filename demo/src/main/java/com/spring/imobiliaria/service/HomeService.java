package com.spring.imobiliaria.service;

import java.util.List;

import com.spring.imobiliaria.dto.HomeDTO;
import com.spring.imobiliaria.model.Home;

public interface HomeService {

	void registerHome(HomeDTO homeDTO, String userId);
	
	void deleteHome(Long id);
	
	Iterable<Home> getAllHomes();
	
	List<Home> getHome(String id);
	
	void updateHome(String id, HomeDTO homeDTO);
	
}
