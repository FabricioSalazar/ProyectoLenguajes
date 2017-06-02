package com.cr.ac.ucr.lenguajes.j2fshop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Producto;

@SpringBootApplication(exclude={MultipartAutoConfiguration.class})
public class J2FShopApplication {
	
	public static List<Producto> carrito;
	
	public static void main(String[] args) {
		SpringApplication.run(J2FShopApplication.class, args);
		carrito = new ArrayList<>();
	}
}
