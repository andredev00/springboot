package com.spring.andre.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.imobiliaria.repository.HomeRepository;

@SpringBootTest
public class HomeTester {

	@Autowired
	HomeRepository homeRepository;
	
	@Test
	void registerHome() {
		
	}
	
	@Test
	void editHome() {
		
	}
	
	@Test
	void deleteHome() {
		
	}
	
}
