package com.spring.andre.demo.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	@NotNull
	@Schema(example = "André Ferreira")
	private String name;
	@Column(name = "email")
	@NotNull
	@Schema(example = "andreferreira6578@gmail.com")
	private String email;
	@Column(name = "county")
	private String county;
	@Column(name = "language")
	private String language;
	@Column(name = "password")
	@NotNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@Schema(example = "My address")
	@Column(name = "address")
	private String address;
	@Schema(example = "My Phone Number")
	@Column(name = "phoneNumber")
	private int phoneNumber;
	@Schema(example = "My Birth Date")
	@Column(name = "dateBirth")
	private Date dateBirth;
	@Schema(example = "Roles")
	@Column(name = "permissions")
	private String permissions;
	@Column(name = "image")
	private String imagePath;
	@Column(name = "imageFileName")
	private String imageFileName;

	public User() {

	}

	public User(String name, String email, String password, String address, int phoneNumber, Date dateBirth,
			String permissions) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dateBirth = dateBirth;
		this.permissions = permissions;
	}

	// create new user
	public User(String name, String email, String password, String address, int phoneNumber, Date dateBirth,
			String permissions, String county, String language) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dateBirth = dateBirth;
		this.permissions = permissions;
		this.county = county;
		this.language = language;
	}

	// edit existing user
	public User(String name, String email, String password, String address, int phoneNumber, Date dateBirth, String county, String language, String permissions) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dateBirth = dateBirth;
		this.county = county;
		this.language = language;
		this.permissions = permissions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
}