package com.spring.andre.demo.service;

import static com.spring.andre.demo.utils.Utils.formatterPriceEuro;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.spring.andre.demo.dto.HomeDTO;
import com.spring.andre.demo.model.Home;
import com.spring.andre.demo.model.User;
import com.spring.andre.demo.repository.HomeRepository;
import com.spring.andre.demo.repository.UserRepository;

@Component
public class HomeService {

	private static final Logger log = LoggerFactory.getLogger(HomeService.class);

	@Autowired
	HomeRepository homeRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	AmazonService amazonService;

	public Home registerHome(HomeDTO homeDTO, MultipartFile multiPartfile, String userId) {
		log.info("Creating a new home");
		Home home = new Home(UUID.randomUUID().toString(), homeDTO);
		home.setPrice(formatterPriceEuro(homeDTO.getPrice()));

//		String file = amazonService.uploadFile(multiPartfile, home.getId());
//		String fileName = file.substring(file.indexOf(" ") + 1);
//		home.setImagePath(AWS_MACHINE_ADDRESS_HOME_IMAGE + fileName);
//		home.setImageFileName(fileName);
			
		if (homeDTO.getUser() != null && !homeDTO.getUser().getId().isEmpty()) {
			User user = userRepository.findById(userId);
			if(user != null) { //TODO, tenho que validar se o utilizador existente é um agente
				homeRepository.save(home);
				log.info("New home created with this properties: " + home.toString());
				
			}
		}
		return home;
	}

	public void deleteHome(Long id) {
		log.info("Deleting home " + id);
		homeRepository.deleteById(id);
		log.info("Home deleted");
	}

	public Iterable<Home> getAllHomes() {
		log.info("Fetching all homes");
		try {
			return homeRepository.findAll();
		} catch (Exception e) {
			log.error("Erro ao aceder ao serviço de procurar todas as casas", e);
		}
		return null;
	}

	public List<Home> getHome(String id) {
		ArrayList<Home> home = new ArrayList<>();
		try {
			log.info("Fetchin a specific home by its id");
			home = homeRepository.findOne(id);
			log.info("Fetched home with following properties " + home.toString());
		} catch (Exception e) {
			log.error("Erro ao aceder ao serviço de procurar da casa", e);
		}

		return home;
	}

	public void updateHome(String id, HomeDTO homeDTO) {
		log.info("Updating home with this id: " + id);
		ArrayList<Home> home = homeRepository.findOne(id);
		Home homeUpdated = new Home(home.get(0).getId(), homeDTO);
		homeRepository.save(homeUpdated);
		log.info("Finished updating home with this id: " + id);
	}
}
