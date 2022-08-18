package com.nlp.tokenize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class TokenizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokenizeApplication.class, args);
    }

}
