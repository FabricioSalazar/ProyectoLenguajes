package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GeneralController {
	@RequestMapping(value = {"/", "/welcome", "/index"}, method = RequestMethod.GET)
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for(Object autho : auth.getAuthorities().toArray()){
			System.out.println(autho.toString());
		}
		if(!auth.getName().equals("anonymousUser"))
			model.addAttribute("username",auth.getName());
		return "index";
	}
}
