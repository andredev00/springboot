package com.spring.andre.demo.dto;

public class HomeDTO {

	private int id;
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
}
