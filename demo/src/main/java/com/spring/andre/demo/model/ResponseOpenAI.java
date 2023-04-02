package com.spring.andre.demo.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("rawtypes")
@Getter
@Setter
public class ResponseOpenAI {
	@JsonProperty("created")
	private long created;

	@JsonProperty("data")
	private ArrayList data;
}
