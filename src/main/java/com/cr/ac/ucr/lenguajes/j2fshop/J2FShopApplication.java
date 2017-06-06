package com.cr.ac.ucr.lenguajes.j2fshop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.cr.ac.ucr.lenguajes.j2fshop.domain.Articulo;

@SpringBootApplication(exclude={MultipartAutoConfiguration.class})
public class J2FShopApplication extends SpringBootServletInitializer {
	
	public static List<Articulo> carrito;
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(J2FShopApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(J2FShopApplication.class, args);
		carrito = new ArrayList<>();
	}
}
