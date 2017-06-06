package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cr.ac.ucr.lenguajes.j2fshop.data.DatosCompañiaDao;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.DatosCompania;

@Controller
public class HeaderController {

	@Autowired
	DatosCompañiaDao datosDao;
	
	@RequestMapping("/header")
	public String iniciar(Model model) {
		return "header";
	}
	
	@RequestMapping("/headerAdmin")
	public String iniciarAdmin(Model model) {
		return "headerAdmin";
	}
	
	@RequestMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("mensaje", "Póngase en contacto con nosotros y nos pondremos en contacto con usted en 24 horas.");
		model.addAttribute("datos", datosDao.findAllCompanyData());
		return "contact";
	}
	
	@RequestMapping("/acercaDe")
	public String acercaDe(Model model) {
		model.addAttribute("datos", datosDao.findAllCompanyData());
		return "acercaDe";
	}
}
