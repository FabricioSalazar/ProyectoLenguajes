package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.cr.ac.ucr.lenguajes.j2fshop.business.UsuarioService;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Role;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Usuario;
import com.cr.ac.ucr.lenguajes.j2fshop.form.UsuarioForm;

@Controller
public class MantenerUsuarioController {
	@Autowired
	UsuarioService usuarioService;
	Usuario userTemp;
	
	//User maintenance, admin first view
	@RequestMapping(value = "/admin/mantenerUsuarios", method = RequestMethod.GET)
	public String mantenerUsuarios(Model model) {
		return "mantenerUsuarios";
	}
	
	//User maintenance, admin view search
	@RequestMapping(value = "/admin/mantenerUsuarios", method = RequestMethod.POST)
	public String mantenerUsuariosBuscar(@RequestParam Map<String, String> requestParams, Model model) {
		model.addAttribute("users",usuarioService.findUserByLogInLike(requestParams.get("login").toString()));
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
	@RequestMapping(value = "/admin/borrarUsuarioConfirm", method = RequestMethod.POST)
	public String eraseUserAdminView(@RequestParam Map<String, String> requestParams, Model model) {
	    userTemp = usuarioService.findUserByLogIn(requestParams.get("username").toString());
		
		model.addAttribute("login",userTemp.getLogin());
		model.addAttribute("idUsuario",userTemp.getIdUsuario());
		
		return "deshabilitarUsuario";
	}
	
	//"Erase" user, admin (disable)
	@RequestMapping(value = "/admin/borrarUsuario", method = RequestMethod.GET)
	public String eraseUserAdmin(@RequestParam Map<String, String> requestParams, Model model) {
		
		if(userTemp != null){
			//disable the user
	        usuarioService.erase(userTemp.getLogin());
		}
		
        
        model.addAttribute("msg", "El usuario "+userTemp.getLogin()+" se ha deshabilitado con éxito!");
        
        userTemp = null;
        
		return "success";
	}
	
	//Enable user, admin
	@RequestMapping(value = "/admin/habilitarUsuario", method = RequestMethod.POST)
	public String enableUserAdmin(@RequestParam Map<String, String> requestParams, Model model) {
	    userTemp = usuarioService.findUserByLogIn(requestParams.get("username").toString());

		if(userTemp != null){
			//enable the user
	        usuarioService.enable(userTemp.getLogin());
	        model.addAttribute("msg", "El usuario "+userTemp.getLogin()+" se ha habilitado con éxito!");
		}
		else
			model.addAttribute("msg", "No se ha encontrado el usuario");
        
        
        userTemp = null;
        
		return "success";
	}
	
	//Change roles
	@RequestMapping(value = "/admin/cambiarRoles", method = RequestMethod.POST)
	public String changeRolesAdmin(@RequestParam Map<String, String> requestParams, Model model) {
	    userTemp = usuarioService.findUserByLogIn(requestParams.get("username").toString());

		if(userTemp != null){
	        model.addAttribute("user", userTemp);
	        for(Role role : userTemp.getRoles()){
	        	model.addAttribute(role.getRoleName(), role.getRoleName());
	        }
		}
        
        userTemp = null;
        
		return "cambiarRoles";
	}
	
	//Change roles
	@RequestMapping(value = "/admin/cambiarRoles/guardar", method = RequestMethod.POST)
	public String changeRolesAdminSave(@RequestParam Map<String, String> requestParams, Model model) {
	    userTemp = usuarioService.findUserByLogIn(requestParams.get("username").toString());

		boolean administrador, cliente, desarrollador;
		
		administrador = requestParams.get("Admin")!=null;
		cliente = requestParams.get("Cliente")!=null;
		desarrollador = requestParams.get("Desarrollador")!=null;
        
		usuarioService.updateRoles(userTemp.getIdUsuario(), administrador,cliente,desarrollador);
        
		return "mantenerUsuarios";
	}
}