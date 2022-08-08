package com.nlp.tokenize.controller;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class restService {
    private static RestTemplate restTemplate = new RestTemplate();
    public String getService(String partUrl){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:5000"+ partUrl, HttpMethod.GET,requestEntity,String.class);
        return response.getBody();
    }
}
