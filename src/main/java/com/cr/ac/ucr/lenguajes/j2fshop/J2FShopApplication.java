package com.cr.ac.ucr.lenguajes.j2fshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cr.ac.ucr.lenguajes.j2fshop.data.UsuarioDao;



@SpringBootApplication
public class J2FShopApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(J2FShopApplication.class, args);
	}
}
