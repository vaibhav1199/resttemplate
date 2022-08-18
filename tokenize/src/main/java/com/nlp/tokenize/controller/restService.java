package com.nlp.tokenize.controller;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class restService {
    private static RestTemplate restTemplate = new RestTemplate();
    public ResponseEntity<String> getService(String partUrl){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);
       return restTemplate.exchange("http://localhost:5000/"+ partUrl, HttpMethod.GET,entity,String.class);
//        return restTemplate.getForObject("http://localhost:5000/"+ partUrl, String.class);
    }
}
