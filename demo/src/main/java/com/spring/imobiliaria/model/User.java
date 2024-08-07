package com.spring.imobiliaria.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.imobiliaria.dto.UserDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "name")
	@Nationalized
	private String name;
	@Column(name = "email", unique = true)
	@Nationalized
	private String email;
	@Column(name = "county")
	@Nationalized
	private String county;
	@Column(name = "language")
	@Nationalized
	private String language;
	@Column(name = "password")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@Column(name = "address")
	@Nationalized
	private String address;
	@Column(name = "phoneNumber")
	private int phoneNumber;
	@Column(name = "dateBirth")
	private Date dateBirth;
	@Column(name = "permissions")
	private String permissions;
	@Column(name = "image")
	private String imagePath;
	@Column(name = "imageFileName")
	private String imageFileName;
	@Column(name = "agentType")
	private String agentType;
	@Column(name = "agentSociety")
	private String agentSociety;
//	@Column(name = "active")
//	private boolean active;
	
	public User(String id, String name, String email, String password, String permissions) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.permissions = permissions;
//		this.active = active;
	}

	public User(UserDTO userDTO) {
		this.name = userDTO.getName();
		this.email = userDTO.getEmail();
		this.county = userDTO.getCounty();
		this.language = userDTO.getLanguage();
		this.password = userDTO.getPassword();
		this.address = userDTO.getAddress();
		this.phoneNumber = userDTO.getPhoneNumber();
		this.dateBirth = userDTO.getDateBirth();
		this.permissions = userDTO.getPermissions();
		this.imagePath = userDTO.getImagePath();
		this.imageFileName = userDTO.getImageFileName();
		this.agentType = userDTO.getAgentType();
		this.agentSociety = userDTO.getAgentSociety();
	}
		
	@Override
	public String toString() {
		return this.name + " + " + this.email + " + " + this.county + " + " + this.language + " + " + this.address
				+ " + " + this.phoneNumber + " + " + this.dateBirth + " + " + this.imagePath + " + "
				+ this.imageFileName + " + " + this.agentType + " + " + this.agentSociety + " + "
				+ "With the following id: " + this.id;
	}
}