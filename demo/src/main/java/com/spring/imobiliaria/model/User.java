package com.spring.imobiliaria.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.imobiliaria.converter.BooleanToStringConverter;
import com.spring.imobiliaria.converter.PermissionsToStringConventer;
import com.spring.imobiliaria.dto.UserDTO;
import com.spring.imobiliaria.enums.PermissionsEnum;

@Entity
@Table(name = "user")

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
	@Convert(converter = PermissionsToStringConventer.class)
	private PermissionsEnum permissions;
	@Column(name = "agentType")
	private String agentType;
	@Column(name = "agentSociety")
	private String agentSociety;
//	@Column(name = "active")
//	private boolean active;
	
	public User() {
		super();
	}

	public User(String id, String name, String email, String password, PermissionsEnum permissions) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.permissions = permissions;
//		this.active = active;
	}

	public User(UserDTO userDTO, String encryptedPass) {
		this.name = userDTO.getName();
		this.email = userDTO.getEmail();
		this.county = userDTO.getCounty();
		this.language = userDTO.getLanguage();
		this.password = encryptedPass;
		this.address = userDTO.getAddress();
		this.phoneNumber = userDTO.getPhoneNumber();
		this.dateBirth = userDTO.getDateBirth();
		this.agentType = userDTO.getAgentType();
		this.agentSociety = userDTO.getAgentSociety();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public PermissionsEnum getPermissions() {
		return permissions;
	}

	public void setPermissions(PermissionsEnum permissions) {
		this.permissions = permissions;
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getAgentSociety() {
		return agentSociety;
	}

	public void setAgentSociety(String agentSociety) {
		this.agentSociety = agentSociety;
	}

	@Override
	public String toString() {
		return this.name + " + " + this.email + " + " + this.county + " + " + this.language + " + " + this.address
				+ " + " + this.phoneNumber + " + " + this.dateBirth + " + " + " + " + this.agentType + " + " + this.agentSociety + " + "
				+ "With the following id: " + this.id;
	}
}