package com.cr.ac.ucr.lenguajes.j2fshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude={MultipartAutoConfiguration.class})
public class J2FShopApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(J2FShopApplication.class, args);
	}
}
