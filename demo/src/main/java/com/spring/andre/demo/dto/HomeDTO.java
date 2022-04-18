package com.spring.andre.demo.dto;

public class HomeDTO {

	private int id;
	private String location;
	private int areaBruta;
	private int totalDoLote;
	private int quartos;
	private String piso;
	private int anoDeConstrucao;
	private int wcs;
	private Boolean estacionamento;
	
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
