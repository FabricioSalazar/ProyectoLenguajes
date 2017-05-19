package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cr.ac.ucr.lenguajes.j2fshop.business.UsuarioService;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Usuario;
import com.cr.ac.ucr.lenguajes.j2fshop.form.UsuarioForm;

@Controller
public class MantenerUsuarioController {
	@Autowired
	UsuarioService usuarioService;
	
	//Show user info
	@RequestMapping(value = "/modificarUsuario", method = RequestMethod.GET)
	public String modifyUserView(UsuarioForm usuarioForm, Model model) {
		Usuario user = usuarioService.findUserByLogIn(SecurityContextHolder.getContext().getAuthentication().getName());
		BeanUtils.copyProperties(user, usuarioForm);
		model.addAttribute("user", user);
		return "modificarUsuario";
	}
	
	//Save new user info
	@RequestMapping(value = "/modificarUsuario", method = RequestMethod.POST)
	public String modifyUserAction(@Valid UsuarioForm usuarioForm,
			BindingResult bindingResult, Model model) {
		//Flag
		boolean errors = false; 
		
		//Current user data
		Usuario user = usuarioService.findUserByLogIn(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("user", user);
		
		//Errors in fields?
		if (bindingResult.hasErrors()) {
			errors = true;
		} 
		
		//New login, already exists?
		if (!usuarioForm.getLogin().equals(user.getLogin())
				&& usuarioService.findUserByLogIn(usuarioForm.getLogin()) != null){
			
			model.addAttribute("errLogin", true);
			errors = true;
			
		}
		
		//No errors!
		if (!errors) {
			int id = user.getIdUsuario();

			BeanUtils.copyProperties(usuarioForm, user);

			user.setIdUsuario(id);

			usuarioService.modify(user);

			model.addAttribute("msg", "Su usuario se ha modificado con éxito");
		}
		
		return errors?"modificarUsuario":"success";
	}
}
