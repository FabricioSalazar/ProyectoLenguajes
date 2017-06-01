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
	
	public void erase(String login) {
		usuarioDao.erase(login);
	}
	
	public void modify(Usuario user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usuarioDao.modify(user);
	}
	
	public Usuario findUserByLogIn(String login) {
		return usuarioDao.findUserByLogIn(login);
	}
	
	public List<Usuario> findUserByLogInLike(String login) {
		return usuarioDao.findUserByLogInLike(login);
	}
	
	public void updateRoles(int idUsuario, boolean administrador, boolean cliente, boolean desarrollador){
		usuarioDao.updateRoles(idUsuario, administrador, cliente,desarrollador);
	}
	
	public List<Usuario> findAllUsers(){
		return usuarioDao.findAllUsers();
	}
	
	public void enable(String login) {
		usuarioDao.enable(login);
	}
}
