package com.spring.andre.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Home")
public class Home {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "localizacao")
	private String location;
	@Column(name = "areaBruta")
	private int areaBruta;
	@Column(name = "totalDoLote")
	private int totalDoLote;
	@Column(name = "quartos")
	private int quartos;
	@Column(name = "piso")
	private String piso;
	@Column(name = "anoDeConstrucao")
	private int anoDeConstrucao;
	@Column(name = "wcs")
	private int wcs;
	@Column(name = "estacionamento")
	private Boolean estacionamento;

	public Home() {

	}

	public Home(int id, String location, int areaBruta, int totalDoLote, int quartos, String piso,
			int anoDeConstrucao, int wcs, Boolean estacionamento) {
		super();
		this.id = id;
		this.location = location;
		this.areaBruta = areaBruta;
		this.totalDoLote = totalDoLote;
		this.quartos = quartos;
		this.piso = piso;
		this.anoDeConstrucao = anoDeConstrucao;
		this.wcs = wcs;
		this.estacionamento = estacionamento;
	}
	
	public Home(String location, int areaBruta, int totalDoLote, int quartos, String piso,
			int anoDeConstrucao, int wcs, Boolean estacionamento) {
		super();
		this.location = location;
		this.areaBruta = areaBruta;
		this.totalDoLote = totalDoLote;
		this.quartos = quartos;
		this.piso = piso;
		this.anoDeConstrucao = anoDeConstrucao;
		this.wcs = wcs;
		this.estacionamento = estacionamento;
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

	public int getAreaBruta() {
		return areaBruta;
	}

	public void setAreaBruta(int areaBruta) {
		this.areaBruta = areaBruta;
	}

	public int getTotalDoLote() {
		return totalDoLote;
	}

	public void setTotalDoLote(int totalDoLote) {
		this.totalDoLote = totalDoLote;
	}

	public int getQuartos() {
		return quartos;
	}

	public void setQuartos(int quartos) {
		this.quartos = quartos;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public int getAnoDeConstrucao() {
		return anoDeConstrucao;
	}

	public void setAnoDeConstrucao(int anoDeConstrucao) {
		this.anoDeConstrucao = anoDeConstrucao;
	}

	public int getWcs() {
		return wcs;
	}

	public void setWcs(int wcs) {
		this.wcs = wcs;
	}

	public Boolean getEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(Boolean estacionamento) {
		this.estacionamento = estacionamento;
	}
}
