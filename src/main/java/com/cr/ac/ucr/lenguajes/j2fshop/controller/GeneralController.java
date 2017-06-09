package com.cr.ac.ucr.lenguajes.j2fshop.controller;


import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cr.ac.ucr.lenguajes.j2fshop.business.UsuarioService;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Usuario;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Articulo;
import com.cr.ac.ucr.lenguajes.j2fshop.storage.SessionManager;


@Controller
public class GeneralController {
	@Autowired
	DataSource dataSource;
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value = {"/", "/welcome", "/index"}, method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for(Object autho : auth.getAuthorities().toArray()){
			if (autho.toString().equals("Administrador")){
				model.addAttribute("admin",true);
			}
		}
		
		if(!auth.getName().equals("anonymousUser")) {
			model.addAttribute("usuario",auth.getName());
			Usuario user = usuarioService.findUserByLogIn(auth.getName());
			SessionManager.setSession(request.getSession().getId(), user, new ArrayList<Articulo>());
			
			if (!user.isEnabled()) {
				usuarioService.enable(auth.getName());
			}
		} 
			
		return "header";
	}
	
	@RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
	public String admin(){
		return "headerAdmin";
	}
	
}
