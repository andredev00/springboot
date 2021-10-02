package com.spring.andre.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.andre.demo.dto.HomeDTO;
import com.spring.andre.demo.model.Home;
import com.spring.andre.demo.repository.HomeRepository;

@Component
public class HomeService {

	@Autowired
	HomeRepository homeRepository;

	public Home registerHome(HomeDTO homeDTO) {
		Home home = new Home();

		home.setAreaBruta(homeDTO.getAreaBruta());
		home.setEstacionamento(homeDTO.getEstacionamento());
		home.setAnoDeConstrucao(homeDTO.getAnoDeConstrucao());
		home.setLocation(homeDTO.getLocation());
		home.setPiso(homeDTO.getPiso());
		home.setQuartos(homeDTO.getQuartos());
		home.setTotalDoLote(homeDTO.getTotalDoLote());
		home.setWcs(homeDTO.getWcs());
		return homeRepository.save(home);

	}

	public void deleteHome(Long id) {
		homeRepository.deleteById(id);
	}
	
	public Iterable<Home> getAllHomes() {
		return homeRepository.findAll();
	}

	public void getHome(Long id) {
		homeRepository.findById(id);
	}

}
