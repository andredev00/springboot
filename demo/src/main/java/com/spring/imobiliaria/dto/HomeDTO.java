package com.spring.imobiliaria.dto;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

public class HomeDTO {

	private String localizacao;
	private String preco;
	private String tamanhoTotal;
	private int quartos;
	private String andar;
	private int anoConstrucao;
	private int wcs;
	private boolean estacionamento;
	// TODO, criar um enum para passar a processar o tipoEstacionamento
//	private Enum tipoEstacionamento;
	private String descricao;
	// TODO, faz sentido conveter para um enum. Apartamento/Terreno/Moradia
	private String tipoImovel;
	// TODO, faz sentido converter para um enum. Venda/Arrendamento/Investimento
	private String tipoDeNegocio;
	private ArrayList<MultipartFile> homeImage;

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getTamanhoTotal() {
		return tamanhoTotal;
	}

	public void setTamanhoTotal(String tamanhoTotal) {
		this.tamanhoTotal = tamanhoTotal;
	}

	public int getQuartos() {
		return quartos;
	}

	public void setQuartos(int quartos) {
		this.quartos = quartos;
	}

	public String getAndar() {
		return andar;
	}

	public void setAndar(String andar) {
		this.andar = andar;
	}

	public int getAnoConstrucao() {
		return anoConstrucao;
	}

	public void setAnoConstrucao(int anoConstrucao) {
		this.anoConstrucao = anoConstrucao;
	}

	public int getWcs() {
		return wcs;
	}

	public void setWcs(int wcs) {
		this.wcs = wcs;
	}

	public boolean isEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(boolean estacionamento) {
		this.estacionamento = estacionamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(String tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public String getTipoDeNegocio() {
		return tipoDeNegocio;
	}

	public void setTipoDeNegocio(String tipoDeNegocio) {
		this.tipoDeNegocio = tipoDeNegocio;
	}

	public ArrayList<MultipartFile> getHomeImage() {
		return homeImage;
	}

	public void setHomeImage(ArrayList<MultipartFile> homeImage) {
		this.homeImage = homeImage;
	}

}
