package com.cr.ac.ucr.lenguajes.j2fshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/resources/**", "/registration").permitAll()
				.antMatchers("/catalogoProductos/**").permitAll()
				.antMatchers("/index", "/header", "/J2FShop", "/", "/login").permitAll()
				.antMatchers("/css/**", "/js/**", "/foundation-icons/**").permitAll()
				.antMatchers("/header.html", "/error").permitAll()
				.antMatchers("/static/", "/images/**").permitAll()
				.antMatchers("/contact","/acercaDe","/carrito").permitAll()
				.antMatchers("/admin/**", "/insertarCategoria","/insertarCategoria/**"
						,"/modificarCategoria/**","/borrarCategoria/**", "/mantenerCategorias"
						,"/mantenerCategorias/**", "/admin").hasAuthority("Administrador")
				.antMatchers("/insertarProducto","/insertarProducto/**",
						"/modificarProducto/**","/borrarProducto/**",
						"/mantenerProductos", "/mantenerProductos/**", "/headearAdmin","/modificarOrganizacion/**").hasAuthority("Administrador")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login").permitAll()
				.and()
				.logout()
				.permitAll();
		
		http.csrf().disable();
	}

	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
}
