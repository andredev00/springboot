package com.spring.imobiliaria.service.impl;

import static com.spring.imobiliaria.utils.Utils.formatterPriceEuro;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.spring.imobiliaria.dto.HomeDTO;
import com.spring.imobiliaria.model.Home;
import com.spring.imobiliaria.model.HomeImage;
import com.spring.imobiliaria.model.User;
import com.spring.imobiliaria.repository.HomeImageRepository;
import com.spring.imobiliaria.repository.HomeRepository;
import com.spring.imobiliaria.repository.UserRepository;
import com.spring.imobiliaria.service.AmazonService;
import com.spring.imobiliaria.service.HomeService;

@Component
public class HomeServiceImpl implements HomeService{

	private static final Logger log = LoggerFactory.getLogger(HomeServiceImpl.class);
	
	@Autowired
	HomeRepository homeRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	HomeImageRepository homeImageRepository;
	
	@Autowired
	AmazonService amazonService;

	@Override
	public void registerHome(HomeDTO homeDTO, String userId) {
		log.info("Creating a new home");
		Home home = new Home(UUID.randomUUID().toString(), homeDTO);
		HomeImage homeImage = new HomeImage(UUID.randomUUID().toString());
		home.setPrice(formatterPriceEuro(homeDTO.getPrice()));

		User user = userRepository.findById(userId);
//		if (user != null && user.getPermissions().equals("ADMIN")) {
//			home.setUser(user);
//			homeRepository.save(home);
//			log.info("New home created with this properties: " + home.toString());
//
//		}
		if (user != null) {
			home.setUser(user);
			homeRepository.save(home);
			log.info("New home created with this properties: " + home.toString());
		}
		
		for (MultipartFile multipartFile : homeDTO.getHomeImage()) {
			homeImage.setHome(home);
			homeImage.setImageFileName(multipartFile.getName());
			homeImage.setHomeImage(parseHomeImageToByte(multipartFile));
			homeImageRepository.save(homeImage);
		}
	}
	
	

	private byte[] parseHomeImageToByte(MultipartFile multipartFile) {
		 byte[] targetArray = null;
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		    int nRead;
		    byte[] data = new byte[4];

		    while ((nRead = multipartFile.getInputStream().read(data, 0, data.length)) != -1) {
		        buffer.write(data, 0, nRead);
		    }

		    buffer.flush();
		    targetArray = buffer.toByteArray();	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return targetArray;
	}



	@Override
	public void deleteHome(Long id) {
		log.info("Deleting home " + id);
		homeRepository.deleteById(id);
		log.info("Home deleted");
	}

	@Override
	public Iterable<Home> getAllHomes() {
		log.info("Fetching all homes");
		try {
			return homeRepository.findAll();
		} catch (Exception e) {
			log.error("Erro ao aceder ao serviço de procurar todas as casas", e);
		}
		return null;
	}

	@Override
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

	@Override
	public void updateHome(String id, HomeDTO homeDTO) {
		log.info("Updating home with this id: " + id);
		ArrayList<Home> home = homeRepository.findOne(id);
		Home homeUpdated = new Home(home.get(0).getId(), homeDTO);
		homeRepository.save(homeUpdated);
		log.info("Finished updating home with this id: " + id);
	}

	
}
