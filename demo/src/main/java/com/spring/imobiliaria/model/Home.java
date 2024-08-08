package com.spring.imobiliaria.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.spring.imobiliaria.dto.HomeDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Home")
@Getter
@Setter
@NoArgsConstructor
public class Home {

	@Id 
	@Column(name = "id")
	private String id;
	@Column(name = "location")
	private String location;
	@Column(name = "price")
	private String price;
	@Column(name = "lot_Total")
	private String lotTotal;
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
	@Column(name = "houseBusinessState")
	private String houseBusinessState;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_user", nullable = false)
	private User user;

	public Home(String id, HomeDTO homeDto) {
		super();
		this.id = id;
		this.location = homeDto.getLocation();
		this.price = homeDto.getPrice();
		this.lotTotal = homeDto.getLotTotal();
		this.room = homeDto.getRoom();
		this.floor = homeDto.getFloor();
		this.constructionYear = homeDto.getConstructionYear();
		this.wcs = homeDto.getWcs();
		this.parking = homeDto.getParking();
		this.description = homeDto.getDescription();
		this.homeType = homeDto.getHomeType();
		this.houseBusinessState = homeDto.getHouseBusinessState();
	}

	@Override
	public String toString() {
		return "location: " + this.location + " + " + "price: " + this.price + " + " + "lotTotal: " + this.lotTotal
				+ " + " + "room: " + this.room + " + " + "floor" + this.floor + " + " + "constructionYear: "
				+ this.constructionYear + " + " + "wcs: " + this.wcs + " + " + "parking: " + this.parking + " + "
				+ "description: " + this.description + " + " + "homeType: " + this.homeType + " + "
				+ "houseBusinessState: " + this.houseBusinessState;
	}

}
