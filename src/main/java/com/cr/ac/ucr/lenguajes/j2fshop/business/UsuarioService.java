package com.cr.ac.ucr.lenguajes.j2fshop.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cr.ac.ucr.lenguajes.j2fshop.data.UsuarioDao;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void save(Usuario user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usuarioDao.save(user);
    }
	
	public Usuario findUserByLogIn(String login) {
		return usuarioDao.findUserByLogIn(login);
	}
	
	public List<Usuario> findAllUsers(){
		return usuarioDao.findAllUsers();
	}
}
