package com.spring.imobiliaria.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.imobiliaria.dto.HomeDTO;
import com.spring.imobiliaria.model.Home;

public interface HomeService {

	void registerHome(HomeDTO homeDTO, MultipartFile multiPartfile, String userId);
	
	void deleteHome(Long id);
	
	Iterable<Home> getAllHomes();
	
	List<Home> getHome(String id);
	
	void updateHome(String id, HomeDTO homeDTO);
	
}
