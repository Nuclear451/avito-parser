package com.opportunity.avitoparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AvitoParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvitoParserApplication.class, args);
	}

}
