package com.spring.andre.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.imobiliaria.dto.UserDTO;
import com.spring.imobiliaria.model.User;
import com.spring.imobiliaria.repository.UserRepository;

@SpringBootTest
public class ClientTester {
	@Autowired
	UserRepository userRepository;
	UserDTO userDto;
	
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	String[] names = { "Maria", "Joana", "Alexandra", "Joaquina", "Manuela" };
	String[] emails = { "maria.ferreira@gmail.com", "joana.ferreira@gmail.com", "alexandra.ferreira@gmail.com",
			"joaquina.ferreira@gmail.com", "manueal.ferreira@gmail.com" };
	
	@Test
	void registerClient() {
		for (int i = 0; i < 5; i++) {
			User user = new User(UUID.randomUUID().toString(), names[i], emails[i], passwordEncoder().encode("teste"),
					"USER", true);
			userRepository.save(user);
			Optional<User> expectedUser = userRepository.findByEmail(emails[i]);
			assertEquals(user.getName(), expectedUser.get().getEmail());
		}
	}
}
