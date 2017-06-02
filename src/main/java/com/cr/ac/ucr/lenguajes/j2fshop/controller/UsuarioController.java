package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cr.ac.ucr.lenguajes.j2fshop.business.SecurityServiceImp;
import com.cr.ac.ucr.lenguajes.j2fshop.business.UsuarioService;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Usuario;
import com.cr.ac.ucr.lenguajes.j2fshop.validator.UserValidator;

@Controller
public class UsuarioController {
	@Autowired
	UsuarioService userService;
	
	@Autowired
	SecurityServiceImp securityService;
	
	@Autowired
	UserValidator userValidator;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new Usuario());

        return "registration";
    }
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	if(bindingResult.hasFieldErrors("login"))
        		model.addAttribute("errorLogin", "Hay un error con el login");
        	if(bindingResult.hasFieldErrors("password"))
        		model.addAttribute("errorPass", "Hay un error con el password");
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getLogin(), userForm.getPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    
    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    
    @RequestMapping(value = {"/disable"}, method = RequestMethod.GET)
    public String disableAccount(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	Usuario user = userService.findUserByLogIn(auth.getName());
        model.addAttribute("idUsuario",user.getIdUsuario());
        model.addAttribute("login", auth.getName());
        return "disable";
    }
    
    @RequestMapping(value = {"/disable"}, method = RequestMethod.POST)
    public String disableAccountPost(HttpServletRequest request, HttpServletResponse response) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	Usuario user = userService.findUserByLogIn(auth.getName());
    	if(user.isEnabled())
    		userService.erase(auth.getName());
        return "redirect:/login?logout";
    }
}
