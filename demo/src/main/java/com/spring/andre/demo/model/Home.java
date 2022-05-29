package com.spring.andre.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "Home")
public class Home {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "parameterValue")
	private int parameterValue;
	@Column(name = "location")
	private String location;
	@Column(name = "price")
	private String price;
	@Column(name = "lot_Total")
	private int lotTotal;
	@Column(name = "room")
	private int room;
	@Column(name = "floor")
	private String floor;
	@Column(name = "constructionYear")
	private int constructionYear;
	@Column(name = "wcs")
	private int wcs;
	@Column(name = "parking")
	private boolean parking;
	@Column(name = "description")
	private String description;
	@Column(name = "homeType")
	private String homeType;
	@Column(name = "image")
	private String imagePath;
	@Column(name="imageFileName")
	private String imageFileName;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_home", referencedColumnName = "id")
//	private User user;

	public Home() {

	}

	public Home(int id, String location, String price, int lotTotal, int room, String floor,
			int constructionYear, int wcs, Boolean parking, String description, String homeType) {
		super();
		this.id = id;
		this.location = location;
		this.price = price;
		this.lotTotal = lotTotal;
		this.room = room;
		this.floor = floor;
		this.constructionYear = constructionYear;
		this.wcs = wcs;
		this.parking = parking;
		this.description = description;
		this.homeType = homeType;
	}
	
	public Home(String location, int lotTotal, int room, String floor,
			int constructionYear, int wcs, Boolean parking, String description, String homeType) {
		super();
		this.location = location;
		this.lotTotal = lotTotal;
		this.room = room;
		this.floor = floor;
		this.constructionYear = constructionYear;
		this.wcs = wcs;
		this.parking = parking;
		this.description = description;
		this.homeType = homeType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(int parameterValue) {
		this.parameterValue = parameterValue;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public boolean getParking() {
		return parking;
	}

	public void setParking(boolean parking) {
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

	@Override
	public String toString() {
		return "location: " + this.location + " + " + "price: " + this.price + " + " + "lotTotal: "
				+ this.lotTotal + " + " + "room: " + this.room + " + " + "floor" + this.floor + " + "
				+ "constructionYear: " + this.constructionYear + " + " + "wcs: " + this.wcs + " + " + "parking: "
				+ this.parking + " + " + "description: " + this.description + " + " + "homeType: " + this.homeType
				+ " + " + "imagePath: " + this.imagePath + " + " + "imageFileName: " + this.imageFileName;
	}
	
}
