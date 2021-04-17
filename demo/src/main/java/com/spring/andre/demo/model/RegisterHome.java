package com.spring.andre.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registarCasa")
public class RegisterHome {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "localizacao")
	private String location;
	@Column(name = "areaBruta")
	private Long areaBruta;
	@Column(name = "totalDoLote")
	private Long totalDoLote;
	@Column(name = "quartos")
	private Long quartos;
	@Column(name = "piso")
	private String piso;
	@Column(name = "anoDeConstrucao")
	private Long anoDeConstrucao;
	@Column(name = "wcs")
	private Long wcs;
	@Column(name = "estacionamento")
	private Boolean estacionamento;
	
	public RegisterHome() {
		
	}

	public RegisterHome(String location, Long areaBruta, Long totalDoLote, Long quartos, String piso, Long anoDeConstrucao,
			Long wcs, Boolean estacionamento) {
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public Long getAreaBruta() {
		return areaBruta;
	}
	public void setAreaBruta(Long areaBruta) {
		this.areaBruta = areaBruta;
	}
	public Long getTotalDoLote() {
		return totalDoLote;
	}
	public void setTotalDoLote(Long totalDoLote) {
		this.totalDoLote = totalDoLote;
	}
	public Long getQuartos() {
		return quartos;
	}
	public void setQuartos(Long quartos) {
		this.quartos = quartos;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public Long getAnoDeConstrucao() {
		return anoDeConstrucao;
	}
	public void setAnoDeConstrucao(Long anoDeConstrucao) {
		this.anoDeConstrucao = anoDeConstrucao;
	}
	public Long getWcs() {
		return wcs;
	}
	public void setWcs(Long wcs) {
		this.wcs = wcs;
	}
	public Boolean getEstacionamento() {
		return estacionamento;
	}
	public void setEstacionamento(Boolean estacionamento) {
		this.estacionamento = estacionamento;
	}
}
