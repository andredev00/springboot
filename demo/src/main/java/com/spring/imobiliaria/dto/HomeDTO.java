package com.spring.imobiliaria.dto;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeDTO {
	
	private String localizacao;
	private String preco;
	private String tamanhoTotal;
	private int quartos;
	private String andar;
	private int anoConstrucao;
	private int wcs;
	private boolean estacionamento;
	//TODO, criar um enum para passar a processar o tipoEstacionamento
//	private Enum tipoEstacionamento;
	private String descricao;
	//TODO, faz sentido conveter para um enum. Apartamento/Terreno/Moradia
	private String tipoImovel;
	//TODO, faz sentido converter para um enum. Venda/Arrendamento/Investimento
	private String tipoDeNegocio;
	private ArrayList<MultipartFile> homeImage;
}
