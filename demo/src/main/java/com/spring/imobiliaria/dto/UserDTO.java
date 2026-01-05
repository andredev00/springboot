package com.spring.imobiliaria.dto;

import java.sql.Date;

import com.spring.imobiliaria.enums.PermissionsEnum;

public class UserDTO {

	private String name;
	private String email;
	private String county;
	private String language;
	private String password;
	private String address;
	private int phoneNumber;
	private Date dateBirth;
	private PermissionsEnum permissions;
	private String agentType;
	private String agentSociety;

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
				+ " + " + this.phoneNumber + " + " + this.dateBirth + " + " + this.agentType + " + "
				+ this.agentSociety;
	}

}
