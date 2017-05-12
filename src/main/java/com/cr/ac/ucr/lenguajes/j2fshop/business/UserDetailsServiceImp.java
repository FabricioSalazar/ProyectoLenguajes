package com.cr.ac.ucr.lenguajes.j2fshop.business;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cr.ac.ucr.lenguajes.j2fshop.data.UsuarioDao;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Role;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Usuario;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
	UsuarioDao usuarioDao;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario user = usuarioDao.findUserByLogIn(login);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
				grantedAuthorities);
	}

}
