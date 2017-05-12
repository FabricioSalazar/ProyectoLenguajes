package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class J2fShopController {

	@RequestMapping("/J2FShop")
	public String iniciar(Model model) {
		return "J2FShop";
	}
	
}
