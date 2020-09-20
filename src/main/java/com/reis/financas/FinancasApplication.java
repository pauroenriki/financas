package com.reis.financas;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinancasApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancasApplication.class, args);
	}
//	 @PostConstruct
//	    public void init(){
//	      // Setting Spring Boot SetTimeZone
//	      TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
//	    }

}
