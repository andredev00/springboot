package com.spring.imobiliaria.service.impl;

import static com.spring.imobiliaria.utils.ServiceUtils.formatterPriceEuro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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
public class HomeServiceImpl implements HomeService {

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
	public ResponseEntity<HomeDTO> registerHome(HomeDTO homeDTO, String userId) throws IOException {
		log.info("Creating a new home");
		Home home = new Home(UUID.randomUUID().toString(), homeDTO);
		home.setPreco(formatterPriceEuro(homeDTO.getPreco()));

		User user = userRepository.findById(userId);
		if (user != null) {
			home.setUser(user);
			homeRepository.save(home);
			log.info("Criação de novo imóvel: " + home.toString());
			return new ResponseEntity<HomeDTO>(homeDTO, HttpStatus.CREATED);
		}

		if (homeDTO.getHomeImage() != null) {
			homeDTO.getHomeImage().forEach(multipartFile -> {
				try {
					HomeImage homeImage = new HomeImage(UUID.randomUUID().toString());
					homeImage.setHome(home);
					homeImage.setImageFileName(multipartFile.getName());
					homeImage.setHomeImage(IOUtils.toByteArray(multipartFile.getInputStream()));
					homeImageRepository.save(homeImage);
				} catch (IOException e) {
					log.error("Erro ao processar as imagens do imóvel: ", e);
					e.printStackTrace();
				}
			});
		}

		log.error("Não existe nenhum agente associado ao imóvel");
		return new ResponseEntity<HomeDTO>(HttpStatus.BAD_REQUEST);
	}

	@Override
	public void deleteHome(Long id) {
		log.info("Apagar imóvel com id:  " + id);
		try {
			homeRepository.deleteById(id);			
		} catch (Exception e) {
			log.error("Ocorreu um erro ao apagar o imóvel com o id: " + id, e);
		}
		
		log.info("Imóvel apagado com o id: " + id);
	}

	@Override
	public ResponseEntity<List<Home>> getAllHomes() {
		log.info("A procurar todos os imóveis");
		List<Home> lstHome = new ArrayList<>();
		try {
			//lstHome = homeRepository.findAll();
			throw new Exception();
		} catch (Exception e) {
			log.error("Ocorreu um erro ao procurar todos os imóveis", e);
			return new ResponseEntity<List<Home>>(new ArrayList<Home>(), HttpStatus.BAD_REQUEST);
		}
		//return new ResponseEntity<List<Home>>(lstHome, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Home> getHome(String id) {
		ArrayList<Home> home = new ArrayList<>();
		try {
			log.info("A procurar imóvel com o id: " + id);
			home = homeRepository.findOne(id);
			log.info("Foi encontrado o imóvel com as seguintes propriedades " + home.toString());
		} catch (Exception e) {
			log.error("Ocorreu um erro ao procurar o imóvel com o id: " + id, e);
			return new ResponseEntity<Home>(HttpStatus.BAD_REQUEST); 
		}
		return new ResponseEntity<Home>(home.get(0), HttpStatus.OK);
	}

	@Override
	public void updateHome(String id, HomeDTO homeDTO) {
		log.info("A atualizar o imóvel com o id : " + id);
		ArrayList<Home> home = homeRepository.findOne(id);
		Home homeUpdated = new Home(home.get(0).getId(), homeDTO);
		try {
			homeRepository.save(homeUpdated);			
		} catch (Exception e) {
			log.error("Ocorreu um erro a atualizar o imóvel com o id: " + id);
		}
		
		log.info("Foi atualizado o imóvel com o id: " + id);
	}

}
