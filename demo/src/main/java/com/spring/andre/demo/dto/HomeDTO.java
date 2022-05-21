package com.spring.andre.demo.dto;

import io.swagger.annotations.ApiModelProperty;

public class HomeDTO {
	
	@ApiModelProperty(hidden = true)
	private String location;
	private int grossArea;
	private int lotTotal;
	private int room;
	private String floor;
	private int constructionYear;
	private int wcs;
	private Boolean parking;
	private String description;
	private String homeType;
	@ApiModelProperty(hidden = true)
	private String imagePath;
	@ApiModelProperty(hidden = true)
	private String imageFileName;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getGrossArea() {
		return grossArea;
	}
	public void setGrossArea(int grossArea) {
		this.grossArea = grossArea;
	}
	public int getLotTotal() {
		return lotTotal;
	}
	public void setLotTotal(int lotTotal) {
		this.lotTotal = lotTotal;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public int getConstructionYear() {
		return constructionYear;
	}
	public void setConstructionYear(int constructionYear) {
		this.constructionYear = constructionYear;
	}
	public int getWcs() {
		return wcs;
	}
	public void setWcs(int wcs) {
		this.wcs = wcs;
	}
	public Boolean getParking() {
		return parking;
	}
	public void setParking(Boolean parking) {
		this.parking = parking;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHomeType() {
		return homeType;
	}
	public void setHomeType(String homeType) {
		this.homeType = homeType;
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
