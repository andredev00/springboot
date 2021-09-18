package com.spring.andre.demo.service;

import com.spring.andre.demo.dto.RegisterHomeDTO;
import com.spring.andre.demo.model.Home;
import com.spring.andre.demo.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomeService {

	@Autowired
	HomeRepository registerHomeRepository;

	public Home registerHome(RegisterHomeDTO registerHomeDTO) {
		Home home = new Home();

		home.setAreaBruta(registerHomeDTO.getAreaBruta());
		home.setEstacionamento(registerHomeDTO.getEstacionamento());
		home.setAnoDeConstrucao(registerHomeDTO.getAnoDeConstrucao());
		home.setLocation(registerHomeDTO.getLocation());
		home.setPiso(registerHomeDTO.getPiso());
		home.setQuartos(registerHomeDTO.getQuartos());
		home.setTotalDoLote(registerHomeDTO.getTotalDoLote());
		home.setWcs(registerHomeDTO.getWcs());
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

}
