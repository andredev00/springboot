package com.spring.andre.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.spring.andre.demo.dto.HomeDTO;
import com.spring.andre.demo.model.Home;
import com.spring.andre.demo.repository.HomeRepository;

@Component
public class HomeService {

	private static final Logger log = LoggerFactory.getLogger(HomeService.class);
	
	@Autowired
	HomeRepository homeRepository;

	public Home registerHome(HomeDTO homeDTO, MultipartFile multiPartfile) {
		String fileUrl = "";
		
		log.info("Creating a new home");
		Home home = new Home(homeDTO.getLocation(), homeDTO.getGrossArea(), homeDTO.getLotTotal(), homeDTO.getRoom(),
				homeDTO.getFloor(), homeDTO.getConstructionYear(), homeDTO.getWcs(), homeDTO.getParking(), homeDTO.getDescription(), homeDTO.getHomeType());
		log.info("New home created with this properties: " + home);
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

	public void updateHome(int id, HomeDTO homeDTO) {
		log.info("Updating home with this id: " + id);
		Home home = homeRepository.findOne(id);
		Home homeUpdated = new Home(home.getId(), homeDTO.getLocation(), homeDTO.getGrossArea(), homeDTO.getLotTotal(),
				homeDTO.getRoom(), homeDTO.getFloor(), homeDTO.getConstructionYear(), homeDTO.getWcs(),
				homeDTO.getParking(), homeDTO.getDescription(), homeDTO.getHomeType());
		log.info("Finished updating home with this id: " + id);
		homeRepository.save(homeUpdated);
	}
}
