package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cr.ac.ucr.lenguajes.j2fshop.business.UsuarioService;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/mostrarUsuarios"  )
	public String iniciar(Model model) {
		model.addAttribute("usuarios", usuarioService.findAllUsers());
		return "mostrarUsuarios";
	}
}
