package com.cr.ac.ucr.lenguajes.j2fshop.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.ac.ucr.lenguajes.j2fshop.data.UsuarioDao;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	public List<Usuario> findAllUsers(){
		usuarioDao.saveImageProduct();
		return usuarioDao.findAllUsers();
	}
}
