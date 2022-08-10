package com.spring.andre.demo.service;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.spring.andre.demo.dto.UserDTO;
import com.spring.andre.demo.model.LoginCredentials;
import com.spring.andre.demo.model.User;
import com.spring.andre.demo.repository.UserRepository;
import com.spring.andre.demo.security.JWTUtil;

@Component
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AmazonService amazonService;

	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public User registerClient(UserDTO userDTO) {
		log.info("Creating a new client with credentials: " + userDTO.getName() + " " + userDTO.getEmail());
		
		Optional<User> userExists = userRepository.findByEmail(userDTO.getEmail());

		if (userExists.isEmpty()) {
			User user = new User(UUID.randomUUID().toString(), userDTO.getName(), userDTO.getEmail(), passwordEncoder().encode(userDTO.getPassword()), "USER");

			log.info("Finished creating a new client with credenials: " + " " + userDTO.getName() + " "
					+ userDTO.getEmail());
			user = userRepository.save(user);
			return user;
		}

		return null;
	}

	public User registerUser(UserDTO userDTO) {
		log.info("Creating admin user with credentials: " + userDTO.getName() + " " + userDTO.getEmail() + " "
				+ userDTO.getPassword());
	
		Optional<User> userExists = userRepository.findByEmail(userDTO.getEmail());

		if (userExists == null || userExists.isEmpty()) {
			User user = new User(UUID.randomUUID().toString(), userDTO.getName(), userDTO.getEmail(), passwordEncoder().encode(userDTO.getPassword()), "ADMIN");

			log.info("Finished creating admin user with credentials: " + userDTO.getName() + " " + userDTO.getEmail()
					+ " " + userDTO.getPassword());
			return userRepository.save(user);
		}

		return null;
	}

	// TODO, this is here because all the authentication logic with be refactor, for
	// using only one object/entity, avoiding repeating code
	public Map<String, Object> login(LoginCredentials body) {
		try {
			String permissions = "";
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getEmail(), body.getPassword());

			authenticationManager.authenticate(authInputToken);

			Optional<User> userExists = userRepository.findByEmail(body.getEmail());
			if (userExists.isPresent()) {
				permissions = userExists.get().getPermissions();
			}

			String token = jwtUtil.generateToken(body.getEmail(), permissions);

			return Collections.singletonMap("jwt", token);
		} catch (Exception e) {
			throw new RuntimeException("Invalid Login Credentials");
		}
	}

	public User editUser(UserDTO userDTO, MultipartFile multipartFile, String id) {
		log.info("Updating information for user: " + userDTO.getEmail());

		User userExists = userRepository.findByGuid(id);

		String name = userDTO.getName() == null ? userExists.getName() : userDTO.getName();
		String email = userDTO.getEmail() == null ? userExists.getEmail() : userDTO.getEmail();
		String address = userDTO.getAddress() == null ? userExists.getAddress() : userDTO.getAddress();
		int phoneNumber = userDTO.getPhoneNumber() < 0 ? userExists.getPhoneNumber() : userDTO.getPhoneNumber();
		Date dataBirth = userDTO.getDateBirth() == null ? userExists.getDateBirth() : userDTO.getDateBirth();
		String county = userDTO.getCounty() == null ? userExists.getCounty() : userDTO.getCounty();
		String language = userDTO.getLanguage() == null ? userExists.getLanguage() : userDTO.getLanguage();
		String permissions = userExists.getPermissions();
		String agentType = userDTO.getAgentType() == null ? userExists.getAgentType() : userDTO.getAgentType();
		String agentSociety = userDTO.getAgentSociety() == null ? userExists.getAgentSociety() : userDTO.getAgentSociety();
		
		String file = amazonService.uploadFile(multipartFile);
		String fileName = file.substring(file.indexOf(" ") + 1);
		User user = new User(name, email, address, phoneNumber, dataBirth, county, language, permissions, agentType, agentSociety);
		user.setImagePath("https://spring-boot-imobiliaria-images-upload.s3.eu-west-2.amazonaws.com/" + fileName);
		user.setImageFileName(fileName);

		log.info("Finished updating information for user: " + userDTO.getEmail());
		userRepository.updateUser(user, id);
		return user;
	}

	public User resetPassword(UserDTO userDTO) {
		log.info("Updating password for user: " + userDTO.getEmail());

		User user = new User(userDTO.getName(), userDTO.getEmail(), passwordEncoder().encode(userDTO.getPassword()),
				userDTO.getPermissions());

		log.info("Finished updating password for user: " + userDTO.getEmail());
		return userRepository.save(user);
	}

	public List<User> getAgents() {
		log.info("Fetching information from our agents list");

		//Pageable firstPageWithTwoElements = PageRequest.of(pageNumber, 2);
		
		List<User> user = userRepository.getAgents();

		log.info("Finished fetching information for our agents list");
		return user;
	}

}
