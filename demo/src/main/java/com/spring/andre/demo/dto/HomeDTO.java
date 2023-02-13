package com.spring.andre.demo.dto;

import com.spring.andre.demo.model.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeDTO {
	
	private String location;
	private String price;
	private String lotTotal;
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
	private User user;
	private String houseBusinessState;
}
