package com.spring.andre.demo.dto;

public class RegisterHomeDTO {

	private String location;
	private Long areaBruta;
	private Long totalDoLote;
	private Long quartos;
	private String piso;
	private Long anoDeConstrucao;
	private Long wcs;
	private Boolean estacionamento;
	
	
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
