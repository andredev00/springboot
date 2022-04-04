package com.spring.andre.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.andre.demo.dto.HomeDTO;
import com.spring.andre.demo.model.Home;
import com.spring.andre.demo.repository.HomeRepository;

@Component
public class HomeService {

	private static final Logger log = LoggerFactory.getLogger(HomeService.class);
	
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
		log.info("Deleting home " + id);
		homeRepository.deleteById(id);	
		log.info("Home deleted");
	}
	
	public Iterable<Home> getAllHomes() {
		log.info("Fetching all homes");
		return homeRepository.findAll();
	}

	public void getHome(Long id) {
		log.info("Fetchin a specific home by its id");
		homeRepository.findById(id);
	}

}
