package com.spring.imobiliaria.service.impl;

import static com.spring.imobiliaria.utils.Constants.AWS_MACHINE_ADDRESS_PROFILE_IMAGE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

import com.spring.imobiliaria.dto.InputDto;
import com.spring.imobiliaria.dto.UserDTO;
import com.spring.imobiliaria.enums.ERole;
import com.spring.imobiliaria.interfaces.UserService;
import com.spring.imobiliaria.model.LoginCredentials;
import com.spring.imobiliaria.model.User;
import com.spring.imobiliaria.repository.UserRepository;
import com.spring.imobiliaria.security.JWTUtil;
import com.spring.imobiliaria.service.AmazonService;
import com.spring.imobiliaria.service.EmailSenderService;
import com.spring.imobiliaria.utils.ERoleConverter;
import com.spring.imobiliaria.utils.Utils;

@Component
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

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

	@Override
	public void createUser(UserDTO userDTO, MultipartFile multiPartFile) {
		log.info("Creating a new client {}: " + userDTO.getName() + " " + userDTO.getEmail());

		Optional<User> userExists = userRepository.findByEmail(userDTO.getEmail());

		try {
			if (userExists.isPresent()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Já existe um user registado com o mesmo email");
			}

			User user = new User(userDTO);
			user.setId(UUID.randomUUID().toString());
			user.setPermissions(Utils.convertToEnum(userDTO.getPermissions()).toString());
//			user.setPermissions(ERoleConverter.roleConverter(ERole.ROLE_USER));
			String file = amazonService.uploadFile(multiPartFile, user.getId());
			String fileName = file.substring(file.indexOf(" ") + 1);
			user.setImagePath(AWS_MACHINE_ADDRESS_PROFILE_IMAGE + fileName);
			user.setImageFileName(fileName);

			user = userRepository.save(user);
			emailService.sendHtmlMessage(userDTO.getName(), userDTO.getEmail(), "");
		} catch (Exception e) {
			log.error("Erro ao criar conta de utilizador  : ");
			log.error("Causa de erro: " + e);
		}

		log.info("Finished creating a new client {} " + " " + userDTO.getName() + " " + userDTO.getEmail());
	}

	public User registerClient(UserDTO userDTO, MultipartFile multiPartFile) throws MessagingException {
		log.info("Creating a new client {}: " + userDTO.getName() + " " + userDTO.getEmail());

		Optional<User> userExists = userRepository.findByEmail(userDTO.getEmail());

		if (userExists.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um user registado com o mesmo email");
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
		log.info("Creating admin user {}: " + userDTO.getName() + " " + userDTO.getEmail() + " "
				+ userDTO.getPassword());

		Optional<User> userExists = userRepository.findByEmail(userDTO.getEmail());

		if (userExists.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um user registado com o mesmo email");
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

	@Override
	public User editUser(UserDTO userDTO, MultipartFile multipartFile, String uuid) {
		log.info("Updating information for user: " + userDTO.getEmail());

		User userExists = userRepository.findById(uuid);

		if (userExists.getId() != null || !userExists.getId().equals("")) {
			String file = amazonService.uploadFile(multipartFile, userExists.getId());
			String fileName = file.substring(file.indexOf(" ") + 1);
			User user = new User(userDTO);
			user.setImagePath("https://spring-boot-imobiliaria-images-upload.s3.eu-west-2.amazonaws.com/" + fileName);
			user.setImageFileName(fileName);
			userRepository.updateUser(user, uuid);
			log.info("Finished updating information for user: " + userDTO.getEmail());
			return user;
		} else {
			return null;
		}

	}

	@Override
	public void resetPassword(String password, String uuid) {
		log.info("Updating password for user: " + uuid);

		userRepository.updatePassword(passwordEncoder().encode(password), uuid);

		log.info("Finished updating password for user: " + uuid);
	}

	@Override
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

	@Override
	public void activeAccount(String uuid) {
		log.info("Activating account for user: " + uuid);
		try {
			userRepository.activeAccount(uuid);
		} catch (Exception e) {
			log.error("Erro ao aceder ao serviço de ativação de conta", e);
		}
		log.info("Finished activating account for user: " + uuid);
	}

	public User getAgentDetail(String name, String uuid) {
		log.info("Getting details from agent with name: " + name);
		try {
			return userRepository.getAgentDetail(name, uuid);
		} catch (Exception e) {
			log.error("Erro ao aceder ao serviço de detalhes do agente", e);
		}
		return null;
	}

	@Override
	public String getProfileImage(String uuid) {
		log.info("Procurar imagem de profile do utilizador: " + uuid);
		try {
			return userRepository.getProfileImage(uuid);
		} catch (Exception e) {
			log.error("Erro ao aceder ao serviço de ativação de conta", e);
		}
		return null;
	}

	public List<InputDto> getDistinctCounty() {
		log.info("Pesquisar os concelhos de agentes sem repetição");
		try {
			List<String> counties = userRepository.findDistinctCounty();

			List<InputDto> lstInputDto = counties.stream().map(n -> {
				InputDto inputDto = new InputDto();
				inputDto.setValue(n);
				inputDto.setLabel(n);
				return inputDto;
			}).collect(Collectors.toList());

			return lstInputDto;
		} catch (Exception e) {
			log.error("Erro ao aceder ao serviço de concelhos de agentes", e);
		}
		return null;
	}

	public List<InputDto> getDistinctName() {
		log.info("Pesquisar users names sem repetição");
		try {
			List<String> name = userRepository.findDistinctName();

			List<InputDto> lstInputDto = name.stream().map(n -> {
				InputDto inputDto = new InputDto();
				inputDto.setValue(n);
				inputDto.setLabel(n);
				return inputDto;
			}).collect(Collectors.toList());

			return lstInputDto;
		} catch (Exception e) {
			log.error("Erro ao aceder ao serviço de nomes de agentes", e);
		}
		return null;
	}

	public List<InputDto> getDistinctAgentType() {
		log.info("Pesquisar users agentType sem repetição");
		try {
			InputDto inputDto = new InputDto();
			List<InputDto> lstInputDto = new ArrayList<>();
			List<String> agentType = userRepository.findDistinctAgentType();

			return null;
//			return utils.convertListToInputData(agentType);

		} catch (Exception e) {
			log.error("Erro ao aceder ao serviço de agentType", e);
		}
		return null;
	}
}
