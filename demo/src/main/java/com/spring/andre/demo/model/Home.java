package com.spring.andre.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "Home")
public class Home {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "location")
	@NotNull
	private String location;
	@Column(name = "gross_Area")
	private int grossArea;
	@Column(name = "lot_Total")
	private int lotTotal;
	@Column(name = "room")
	private int room;
	@Column(name = "floor")
	private String floor;
	@Column(name = "construction_Year")
	private int constructionYear;
	@Column(name = "wcs")
	private int wcs;
	@Column(name = "parking")
	private Boolean parking;

	public Home() {

	}

	public Home(int id, String location, int grossArea, int lotTotal, int room, String floor,
			int constructionYear, int wcs, Boolean parking) {
		super();
		this.id = id;
		this.location = location;
		this.grossArea = grossArea;
		this.lotTotal = lotTotal;
		this.room = room;
		this.floor = floor;
		this.constructionYear = constructionYear;
		this.wcs = wcs;
		this.parking = parking;
	}
	
	public Home(String location, int grossArea, int lotTotal, int room, String floor,
			int constructionYear, int wcs, Boolean parking) {
		super();
		this.location = location;
		this.grossArea = grossArea;
		this.lotTotal = lotTotal;
		this.room = room;
		this.floor = floor;
		this.constructionYear = constructionYear;
		this.wcs = wcs;
		this.parking = parking;
	}

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

	public void setAreaBruta(int grossArea) {
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
}
