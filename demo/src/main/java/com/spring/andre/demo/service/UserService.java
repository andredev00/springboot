package com.spring.andre.demo.service;

import static com.spring.andre.demo.utils.Constants.AWS_MACHINE_ADDRESS_PROFILE_IMAGE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;

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

	public User registerClient(UserDTO userDTO, MultipartFile multiPartFile) throws MessagingException {
		log.info("Creating a new client {}: " + userDTO.getName() + " " + userDTO.getEmail());

		Optional<User> userExists = userRepository.findByEmail(userDTO.getEmail());

		if (!userExists.isPresent()) {
			return null;
		}

		User user = new User(userDTO);
		user.setId(UUID.randomUUID().toString());
		user.setPermissions(ERoleConverter.roleConverter(ERole.ROLE_USER));
		String file = amazonService.uploadFile(multiPartFile, user.getId());
		String fileName = file.substring(file.indexOf(" ") + 1);
		user.setImagePath(AWS_MACHINE_ADDRESS_PROFILE_IMAGE + fileName);
		user.setImageFileName(fileName);

		user = userRepository.save(user);
		emailService.sendHtmlMessage(userDTO.getName(), userDTO.getEmail(), "");
		log.info("Finished creating a new client {} " + " " + userDTO.getName() + " " + userDTO.getEmail());
		return user;
	}

	public User registerUser(UserDTO userDTO, MultipartFile multiPartFile) throws MessagingException {
		log.info("Croeating admin user {}: " + userDTO.getName() + " " + userDTO.getEmail() + " "
				+ userDTO.getPassword());

		Optional<User> userExists = userRepository.findByEmail(userDTO.getEmail());

		if (!userExists.isPresent()) {
			return null;
		}

		User user = new User(userDTO);
		user.setId(UUID.randomUUID().toString());
		user.setPermissions(ERoleConverter.roleConverter(ERole.ROLE_ADMIN));
		String file = amazonService.uploadFile(multiPartFile, user.getId());
		String fileName = file.substring(file.indexOf(" ") + 1);
		user.setImagePath(AWS_MACHINE_ADDRESS_PROFILE_IMAGE + fileName);
		user.setImageFileName(fileName);

		log.info("Finished creating admin user {}: " + userDTO.getName() + " " + userDTO.getEmail() + " "
				+ userDTO.getPassword());
		user = userRepository.save(user);
		emailService.sendHtmlMessage(userDTO.getName(), userDTO.getEmail(), "");
		return user;
	}

	public Map<String, Object> login(LoginCredentials body) {
		String permissions = "";
		String personName = "";
		UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(body.getEmail(),
				body.getPassword());
		try {
			authenticationManager.authenticate(authInputToken);
		} catch (AuthenticationException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", e);
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

		User userExists = userRepository.findById(id);
		
		if (userExists.getId() != null || !userExists.getId().equals("")) {
			String file = amazonService.uploadFile(multipartFile, userExists.getId());
			String fileName = file.substring(file.indexOf(" ") + 1);
			User user = new User(userDTO);
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
		List<User> user = new ArrayList<>();
		log.info("Fetching information from our agents list");
		try {
			user = userRepository.getAgents();
		} catch (Exception e) {
			log.error("Erro ao aceder ao serviço de procura de agents", e);
		}
		log.info("Finished fetching information for our agents list");
		return user;
	}

	public void activeAccount(String uuid) {
		log.info("Activating account for user: " + uuid);
		try {
			userRepository.activeAccount(uuid);
		} catch (Exception e) {
			log.error("Erro ao aceder ao serviço de ativação de conta", e);
		}
		log.info("Finished activating account for user: " + uuid);
	}

	public User getAgentDetail(String name, String id) {
		log.info("Getting details from agent with name: " + name);
		try {
			return userRepository.getAgentDetail(name, id);
		} catch (Exception e) {
			log.error("Erro ao aceder ao serviço de detalhes do agente", e);
		}
		return null;
	}

	public String getProfileImage(String uuid) {
		log.info("Procurar imagem de profile do utilizador: " + uuid);
		try {
			return userRepository.getProfileImage(uuid);
		} catch (Exception e) {
			log.error("Erro ao aceder ao serviço de ativação de conta", e);
		}
		return null;
	}

	public List<User> getDistinctUsers() {
		List<User> user = new ArrayList<User>();
		log.info("Pesquisar users sem repetição");
		try {
			user =  userRepository.findDistinctCounty();
		} catch (Exception e) {
			log.error("Erro ao aceder ao serviço de ativação de conta", e);
		}
		return user;
	}
}
