package com.cr.ac.ucr.lenguajes.j2fshop.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cr.ac.ucr.lenguajes.j2fshop.business.UsuarioService;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Usuario;

@Component
public class UserValidator implements Validator {
	@Autowired
	UsuarioService userService;

	@Override
	public boolean supports(Class<?> arg0) {
		return Usuario.class.equals(arg0);
	}

	
	//TODO Validar el resto de campos!!!! 
	@Override
	public void validate(Object o, Errors errors) {
		Usuario user = (Usuario) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "NotEmpty");
		if (user.getLogin().length() < 6 || user.getLogin().length() > 256) {
			errors.rejectValue("login", "Size.userForm.accountName");
		}
		
		if (userService.findUserByLogIn(user.getLogin()) != null) {
			errors.rejectValue("login", "Duplicate.userForm.accountName");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}

	}
}
