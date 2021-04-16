package com.spring.andre.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.andre.demo.dto.ClientDTO;
import com.spring.andre.demo.model.Client;
import com.spring.andre.demo.repository.ClientRepository;

@Component
public class ClientService {
	
	@Autowired
	ClientRepository clientRepository;
	
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public Client registerClient(ClientDTO clientDTO) {
		Client client = new Client();
		
		client.setName(clientDTO.getName());
		client.setUsername(clientDTO.getUsername());
		client.setPassword(passwordEncoder().encode(clientDTO.getPassword()));
		
		return clientRepository.save(client);
	}

}
