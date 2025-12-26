package com.spring.imobiliaria.dto;

import com.spring.imobiliaria.model.Home;

import lombok.Getter;
import lombok.Setter;

public class HomeImageDTO {

	private String id;
	private byte[] homeImage;
	private String imageFileName;
	private Home home;

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
