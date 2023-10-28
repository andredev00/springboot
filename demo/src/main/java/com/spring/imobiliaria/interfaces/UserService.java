package com.spring.imobiliaria.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.imobiliaria.dto.InputDto;
import com.spring.imobiliaria.dto.UserDTO;
import com.spring.imobiliaria.model.User;

public interface UserService {
	
	void createUser(UserDTO userDTO, MultipartFile multipartFile);
	
	List<User> getAgents();

	List<InputDto> getDistinctAgentType();
	
	List<InputDto> getDistinctName();
	
	List<InputDto> getDistinctCounty();
	
	String getProfileImage(String uuid);
	
	User getAgentDetail(String name, String uuid);
	
	boolean activeAccount(String uuid);
	
	void resetPassword(String password, String uuid);
	
	User editUser(UserDTO userDTO, MultipartFile multipartFile, String uuid);
}  
