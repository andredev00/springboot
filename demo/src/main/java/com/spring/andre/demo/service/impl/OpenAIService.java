package com.spring.andre.demo.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.spring.andre.demo.model.ResponseOpenAI;

@Component
public class OpenAIService {

	private static final String OPENAI_URL = "https://api.openai.com/v1/images/generations";
	private final String apiKey = "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";
	private final RestTemplate restTemplate = new RestTemplate();

	// PROMPT, pode ser o valor que é passado na descricao da propriedade
	public String generateImages(String prompt) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + apiKey);

		// We are including only some of the parameters to the json request
		String requestJson = "{\"prompt\":\"" + prompt + "\",\"n\":" + 1 + "}"; // Corrigir o parametro "n"

		HttpEntity<String> request = new HttpEntity<>(requestJson, headers);
		ResponseEntity<ResponseOpenAI> response = restTemplate.postForEntity(OPENAI_URL, request, ResponseOpenAI.class);
		return StringUtils.substringBetween(response.getBody().getData().get(0).toString(), "=", "}");
	}

	public static void main(String[] args) {
		OpenAIService openAI = new OpenAIService();
		String teste = openAI.generateImages("Moradia em cascais familia grande");
		System.out.println(teste);
	}

}
