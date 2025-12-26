package com.spring.imobiliaria.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.spring.imobiliaria.converter.BooleanToStringConverter;
import com.spring.imobiliaria.dto.HomeDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Home")
@NoArgsConstructor
public class Home {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "localizacao")
	private String localizacao;
	@Column(name = "preco")
	private String preco;
	@Column(name = "tamanhoTotal")
	private String tamanhoTotal;
	@Column(name = "quartos")
	private int quartos;
	@Column(name = "andar")
	private String andar;
	@Column(name = "anoConstrucao")
	private int anoConstrucao;
	@Column(name = "wcs")
	private int wcs;
	@Convert(converter = BooleanToStringConverter.class)
	@Column(name = "estacionamento")
	private boolean estacionamento;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "tipoImovel")
	private String tipoImovel;
	@Column(name = "tipoDeNegocio")
	private String tipoDeNegocio;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_user", nullable = false)
	private User user;

	public Home(String id, HomeDTO homeDto) {
		super();
		this.id = id;
		this.localizacao = homeDto.getLocalizacao();
		this.preco = homeDto.getPreco();
		this.tamanhoTotal = homeDto.getTamanhoTotal();
		this.quartos = homeDto.getQuartos();
		this.andar = homeDto.getAndar();
		this.anoConstrucao = homeDto.getAnoConstrucao();
		this.wcs = homeDto.getWcs();
		this.estacionamento = homeDto.isEstacionamento();
		this.descricao = homeDto.getDescricao();
		this.tipoImovel = homeDto.getTipoImovel();
		this.tipoDeNegocio = homeDto.getTipoDeNegocio();
	}

	@Override
	public String toString() {
		return "localizacao: " + this.localizacao + " + " + "preco: " + this.preco + " + " + "tamanhoTotal: "
				+ this.tamanhoTotal + " + " + "quartos: " + this.quartos + " + " + "andar" + this.andar + " + "
				+ "anoConstrucao: " + this.anoConstrucao + " + " + "wcs: " + this.wcs + " + " + "estacionamento: "
				+ this.estacionamento + " + " + "descricao: " + this.descricao + " + " + "tipoImovel: "
				+ this.tipoImovel + " + " + "tipoDeNegocio: " + this.tipoDeNegocio;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
