package com.reis.financas;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FinancasApplication.class);
	}
	
//	 @PostConstruct
//	    public void init(){
//	      // Setting Spring Boot SetTimeZone
//	      TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
//	    }

}
