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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "HomeImage")
@Getter
@Setter
@NoArgsConstructor
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
}
