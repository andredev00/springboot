package com.spring.andre.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.spring.andre.demo.dto.UserDTO;
import com.spring.andre.demo.enums.ERole;
import com.spring.andre.demo.model.LoginCredentials;
import com.spring.andre.demo.model.User;
import com.spring.andre.demo.repository.UserRepository;
import com.spring.andre.demo.security.JWTUtil;
import com.spring.andre.demo.utils.ERoleConverter;

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
		log.info("Creating a new client {}: " + userDTO.getName() + " " + userDTO.getEmail());

		Optional<User> userExists = userRepository.findByEmail(userDTO.getEmail());

		if(!userExists.isEmpty()) {
			return null;
		}
	
		User user = new User(UUID.randomUUID().toString(), userDTO.getName(), userDTO.getEmail(),
				passwordEncoder().encode(userDTO.getPassword()), ERoleConverter.roleConverter(ERole.ROLE_USER), false);

		log.info("Finished creating a new client {} " + " " + userDTO.getName() + " "
				+ userDTO.getEmail());
		user = userRepository.save(user);
		emailService.sendHtmlMessage(userDTO.getName(), userDTO.getEmail(), "");
		return user;
	}

	public User registerUser(UserDTO userDTO) throws MessagingException {
		log.info("Croeating admin user {}: " + userDTO.getName() + " " + userDTO.getEmail() + " "
				+ userDTO.getPassword());

		Optional<User> userExists = userRepository.findByEmail(userDTO.getEmail());
		
		if (!userExists.isEmpty()) {
			return null;
		}

		User user = new User(UUID.randomUUID().toString(), userDTO.getName(), userDTO.getEmail(),
				passwordEncoder().encode(userDTO.getPassword()), ERoleConverter.roleConverter(ERole.ROLE_ADMIN), false);

		log.info("Finished creating admin user {}: " + userDTO.getName() + " " + userDTO.getEmail()
				+ " " + userDTO.getPassword());
		user = userRepository.save(user);
		emailService.sendHtmlMessage(userDTO.getName(), userDTO.getEmail(), "");
		return user;
	}

	// TODO, this is here because all the authentication logic with be refactor, for
	// using only one object/entity, avoiding repeating code
	public Map<String, Object> login(LoginCredentials body, HttpServletResponse response) {
		String permissions = "";
		String personName = "";
		UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
				body.getEmail(), body.getPassword());
		try {
			authenticationManager.authenticate(authInputToken);
		} catch (AuthenticationException e) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "User Not Found", e);
		}
		Optional<User> userExists = userRepository.findByEmail(body.getEmail());
		if (userExists.isPresent()) {
			permissions = userExists.get().getPermissions();
			personName = userExists.get().getName();
			String token = jwtUtil.generateToken(body.getEmail(), permissions, personName);
			
			return Collections.singletonMap("jwt", token);
		}
		return null;
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

	public User getAgentDetail(String name, String id) {
		log.info("Getting details from agent with name: " + name);
		return userRepository.getAgentDetail(name, id);
	}

}
