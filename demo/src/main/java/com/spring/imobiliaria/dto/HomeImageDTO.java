package com.spring.imobiliaria.dto;

import com.spring.imobiliaria.model.Home;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeImageDTO {


	private String id;
	private byte[] homeImage;
	private String imageFileName;
	private Home home;	
}
