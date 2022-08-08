package com.nlp.tokenize.controller;

import org.springframework.web.client.RestTemplate;

public class restService {
    private static RestTemplate restTemplate = new RestTemplate();
    public String getService(String partUrl){
        return restTemplate.getForObject("http://localhost:5000/"+ partUrl, String.class);
    }
}
