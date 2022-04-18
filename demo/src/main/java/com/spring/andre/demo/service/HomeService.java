package com.spring.andre.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.andre.demo.dto.HomeDTO;
import com.spring.andre.demo.model.Home;
import com.spring.andre.demo.repository.HomeRepository;

@Component
public class HomeService {

	@Autowired
	HomeRepository registerHomeRepository;

	public Home registerHome(HomeDTO homeDTO) {
		Home home = new Home(homeDTO.getLocation(), homeDTO.getAreaBruta(), homeDTO.getTotalDoLote(),
				homeDTO.getQuartos(), homeDTO.getPiso(), homeDTO.getAnoDeConstrucao(), homeDTO.getWcs(),
				homeDTO.getEstacionamento());
		return registerHomeRepository.save(home);

	}

	public void deleteHome(Long id) {
		registerHomeRepository.deleteById(id);
	}
	
	public Iterable<Home> getAllHomes() {
		return registerHomeRepository.findAll();
	}

	public void getHome(Long id) {
		registerHomeRepository.findById(id);
	}
	
	//TODO, it will be improved
		public void updateHome(int id, HomeDTO homeDTO) {
			Home home = registerHomeRepository.findOne(id);
			home.setLocation(homeDTO.getLocation());
			home.setAreaBruta(homeDTO.getAreaBruta());
			home.setTotalDoLote(homeDTO.getTotalDoLote());
			home.setQuartos(homeDTO.getQuartos());
			home.setPiso(homeDTO.getPiso());
			home.setAnoDeConstrucao(homeDTO.getAnoDeConstrucao());
			home.setWcs(homeDTO.getWcs());
			home.setEstacionamento(homeDTO.getEstacionamento());
			registerHomeRepository.save(home);
		}
}
