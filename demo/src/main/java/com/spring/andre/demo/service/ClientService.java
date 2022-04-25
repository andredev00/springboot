package com.spring.andre.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.andre.demo.dto.ClientDTO;
import com.spring.andre.demo.model.Client;
import com.spring.andre.demo.repository.ClientRepository;

@Component
public class ClientService {

	private static final Logger log = LoggerFactory.getLogger(ClientService.class);
	
	@Autowired
	ClientRepository clientRepository;
	
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	public Client registerClient(ClientDTO clientDTO) {
		log.info("Creating a new user with credentials: " + clientDTO.getName() + " " + clientDTO.getPassword() + " " + clientDTO.getEmail());
		
		Client client = new Client(clientDTO.getName(), passwordEncoder().encode(clientDTO.getPassword()), clientDTO.getEmail());
		
		log.info("Finished creating a new user with credenials: " + " " + clientDTO.getName() + " " + clientDTO.getPassword() + " " + clientDTO.getEmail());
		return clientRepository.save(client);
	}

}

