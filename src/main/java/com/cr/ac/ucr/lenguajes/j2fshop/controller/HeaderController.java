package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeaderController {

	@RequestMapping("/header")
	public String iniciar(Model model) {
		return "header";
	}
		
}
