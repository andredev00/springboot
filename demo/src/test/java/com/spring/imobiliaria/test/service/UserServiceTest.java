package com.spring.imobiliaria.test.service;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.spring.imobiliaria.interfaces.UserService;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
//	@Test
//	void getAgents() {
//		List<User> user = userService.getAgents();
//		assertEquals(user.get(0).getName(), "Bernardo");
//	}
//	
//	@Test
//	void getProfileImage() {
//		String imageProfile = userService.getProfileImage("7b2fae87-178a-4a4a-a328-9f94a026bb28");
//		assertNull(imageProfile);
//	}
//	
//	@Test
//	void getAgentDetail() {
//		User user = userService.getAgentDetail("Bernardo", "7b2fae87-178a-4a4a-a328-9f94a026bb28");
//		assertEquals(user.getName(), "Bernardo");
//	}
//	
//	@Test
//	void getDistinctName() {
//		List<InputDto> name = userService.getDistinctName();
//		assertEquals(name.get(0).getLabel(), "Bernardo");
//	}
	
//	@Test
//	void editUser() {
//		UserDTO userDTO = new UserDTO();
//		userDTO.setName("Pedro");
//		User user = userService.editUser(userDTO, null, "7b2fae87-178a-4a4a-a328-9f94a026bb28");
//		assertEquals(user.getName(), "Pedro");
//	}
	
	@Test
	void activeAccount() {
		assertTrue(userService.activeAccount("7b2fae87-178a-4a4a-a328-9f94a026bb28"));
	}
}