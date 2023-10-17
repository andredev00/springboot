package com.spring.imobiliaria.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private String name;
	private String email;
	private String county;
	private String language;
	private String password;
	private String address;
	private int phoneNumber;
	private Date dateBirth;
	private String permissions;
	private String imagePath;
	private String imageFileName;
	private String agentType;
	private String agentSociety;

	@Override
	public String toString() {
		return this.name + " + " + this.email + " + " + this.county + " + " + this.language + " + " + this.address
				+ " + " + this.phoneNumber + " + " + this.dateBirth + " + " + this.imagePath + " + "
				+ this.imageFileName + " + " + this.agentType + " + " + this.agentSociety;
	}
}
