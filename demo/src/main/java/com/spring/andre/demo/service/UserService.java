package com.spring.andre.demo.service;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private EmailSenderService emailService;
	
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public User registerClient(UserDTO userDTO) throws MessagingException {
		log.info("Creating a new client with credentials: " + userDTO.getName() + " " + userDTO.getEmail());

		Optional<User> userExists = userRepository.findByEmail(userDTO.getEmail());

		if (userExists.isEmpty()) {
			User user = new User(UUID.randomUUID().toString(), userDTO.getName(), userDTO.getEmail(),
					passwordEncoder().encode(userDTO.getPassword()), "USER", false);

			log.info("Finished creating a new client with credenials: " + " " + userDTO.getName() + " "
					+ userDTO.getEmail());
			user = userRepository.save(user);
			emailService.sendHtmlMessage("andreferreira6578@outlook.pt", userDTO.getEmail(), "", userDTO.getName(),
					"welcome-email", "Seja Bem-Vindo");
			return user;
		}

		return null;
	}

	public User registerUser(UserDTO userDTO) throws MessagingException {
		log.info("Creating admin user with credentials: " + userDTO.getName() + " " + userDTO.getEmail() + " "
				+ userDTO.getPassword());

		Optional<User> userExists = userRepository.findByEmail(userDTO.getEmail());

		if (userExists == null || userExists.isEmpty()) {
			User user = new User(UUID.randomUUID().toString(), userDTO.getName(), userDTO.getEmail(),
					passwordEncoder().encode(userDTO.getPassword()), "ADMIN", false);

			log.info("Finished creating admin user with credentials: " + userDTO.getName() + " " + userDTO.getEmail()
					+ " " + userDTO.getPassword());
			user = userRepository.save(user);
			emailService.sendHtmlMessage("andreferreira6578@outlook.pt", userDTO.getEmail(), "", userDTO.getName(),
					"welcome-email", "Seja Bem-Vindo");
			return user;
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

		if (userExists.getId() != null || !userExists.getId().equals("")) {
			String file = amazonService.uploadFile(multipartFile);
			String fileName = file.substring(file.indexOf(" ") + 1);
			User user = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getAddress(), userDTO.getPhoneNumber(),
					userDTO.getDateBirth(), userDTO.getCounty(), userDTO.getLanguage(), userDTO.getPermissions(),
					userDTO.getAgentType(), userDTO.getAgentSociety());
			user.setImagePath("https://spring-boot-imobiliaria-images-upload.s3.eu-west-2.amazonaws.com/" + fileName);
			user.setImageFileName(fileName);
			userRepository.updateUser(user, id);
			log.info("Finished updating information for user: " + userDTO.getEmail());
			return user;
		} else {
			return null;
		}

	}

	public void resetPassword(String password, String uuid) {
		log.info("Updating password for user: " + uuid);
		
		userRepository.updatePassword(passwordEncoder().encode(password), uuid);

		log.info("Finished updating password for user: " + uuid);
	}

	public List<User> getAgents() {
		log.info("Fetching information from our agents list");
		
		List<User> user = userRepository.getAgents();

		log.info("Finished fetching information for our agents list");
		return user;
	}

	public void activeAccount(String uuid) {
		log.info("Activating account for user: " + uuid);
		
		userRepository.activeAccount(uuid);
		
		log.info("Finished activating account for user: " + uuid);
		
	}

}
