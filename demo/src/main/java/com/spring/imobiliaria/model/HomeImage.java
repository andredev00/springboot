package com.spring.imobiliaria.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "HomeImage")
public class HomeImage {

	@Id
	@Column(name = "id")
	private String id;
	@Lob
	@Column(name = "home_image")
	private byte[] homeImage;
	@Column(name = "imageFileName")
	private String imageFileName;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "home_id", nullable = false)
	@JsonIgnore
	private Home home;

	public HomeImage(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getHomeImage() {
		return homeImage;
	}

	public void setHomeImage(byte[] homeImage) {
		this.homeImage = homeImage;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}
}
