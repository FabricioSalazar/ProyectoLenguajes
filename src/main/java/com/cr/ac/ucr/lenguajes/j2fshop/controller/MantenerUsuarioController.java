package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
	
	//User maintenance, admin view
	@RequestMapping(value = "/admin/mantenerUsuarios", method = RequestMethod.GET)
	public String mantenerUsuarios(Model model) {
		model.addAttribute("users", usuarioService.findAllUsers());
		return "mantenerUsuarios";
	}

	//Modify, show user info
	@RequestMapping(value = "/modificarUsuario", method = RequestMethod.GET)
	public String modifyUserView(UsuarioForm usuarioForm, Model model) {
		Usuario user = usuarioService.findUserByLogIn(SecurityContextHolder.getContext().getAuthentication().getName());
		BeanUtils.copyProperties(user, usuarioForm);
		model.addAttribute("user", user);
		return "modificarUsuario";
	}
	
	//Modify, save new user info
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

	//"Erase" user view (disable)
	@RequestMapping(value = "/borrarUsuario", method = RequestMethod.GET)
	public String eraseUserView() {
		return "erase";
	}
	
	//"Erase" user (disable)
	@RequestMapping(value = "/borrarUsuario", method = RequestMethod.POST)
	public String eraseUserView(Model model, HttpServletRequest request, HttpServletResponse response) {
		//Get the login
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//Log out the current user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
		
        //disable the user
        usuarioService.erase(username);
        
		model.addAttribute("msg");
		return "login";
	}
	
	//"Erase" user, admin view (disable)
	@RequestMapping(value = "/admin/borrarUsuario", method = RequestMethod.GET)
	public String eraseUserAdminView() {
		return "erase";
	}
	
	//"Erase" user, admin (disable)
	@RequestMapping(value = "/admin/borrarUsuario", method = RequestMethod.POST)
	public String eraseUserAdminView(HttpServletRequest request) {
		
		//disable the user
        //usuarioService.erase(request.getAttribute("username").toString());
        
		return "mantenerUsuarios";
	}
	
}