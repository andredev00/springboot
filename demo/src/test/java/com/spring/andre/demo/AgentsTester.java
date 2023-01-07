package com.spring.andre.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.andre.demo.model.User;
import com.spring.andre.demo.repository.UserRepository;

@SpringBootTest
class AgentsTester {
	 
	@Autowired
	UserRepository userRepository;
	
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	String[] names = { "Andre", "Pedro", "Joao", "Roberto", "Daniel", "Sandro", "Hugo", "Joaquim", "David", "Diogo",
			"Bernardo", "Sara", "Andre", "Andre", "Andre", "Andre", "Andre", "Andre", "Andre", "Andre", "Andre",
			"Andre", "Andre", "Andre" };
	String[] emails = { "andre.ferreira@gmail.com", "pedro.ferreira@decode.pt", "joao.ferreira@decode.pt",
			"roberto.ferreira@decode.pt", "daniel.ferreira@decode.pt", "sandro.ferreira@decode.pt",
			"hugo.ferreira@decode.pt", "joaquim.ferreira@decode.pt", "david.ferreira@decode.pt",
			"diogo.ferreira@decode.pt", "bernardo.ferreira@decode.pt", "sara.ferreira@decode.pt",
			"andre.ferreira1@decode.pt", "andre.ferreira2@decode.pt", "andre.ferreira3@decode.pt",
			"andre.ferreira4@decode.pt", "andre.ferreira5@decode.pt", "andre.ferreira6@decode.pt",
			"andre.ferreira7@decode.pt", "andre.ferreira8@decode.pt", "andre.ferreira9@decode.pt",
			"andre.ferreira10@decode.pt", "andre.ferreira11@decode.pt", "andre.ferreira12@decode.pt" };
	
	@Test
	void registerAgents() {
		User user = new User();
		for (int i = 0; i<24; i++) {
			user.setId(UUID.randomUUID().toString());
			user.setName(names[i]);	
			user.setEmail(emails[i]);
			user.setPassword(passwordEncoder().encode("teste"));
			user.setPermissions("ADMIN");
			userRepository.save(user);
			assertEquals(names[i], user.getName());
		}
	}
}
