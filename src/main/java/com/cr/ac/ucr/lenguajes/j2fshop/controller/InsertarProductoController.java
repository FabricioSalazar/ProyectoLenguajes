package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InsertarProductoController {

	
	@RequestMapping("insertarProducto")
	public String iniciar(Model model) {
		return "insertarProducto";
	}
	
}
