package com.cr.ac.ucr.lenguajes.j2fshop.domain;

import java.util.LinkedList;

public class Usuario {

	private int idUsuario;
	private String nombre;
	private String apellidos;
	private String login;
	private String password;
	private boolean enabled;
	private String dirrecion1;
	private String dirrecion2;
	private String pais;
	private String ciudad;
	private String estado;
	private String codigoPostal;
	private String telefono;
	private String numeroTarjeta;
	private String ccv;
	private float saldo;
	private LinkedList<Role> roles;
	
	public Usuario() {
		this.roles=new LinkedList<>();
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getDirrecion1() {
		return dirrecion1;
	}

	public void setDirrecion1(String dirrecion1) {
		this.dirrecion1 = dirrecion1;
	}

	public String getDirrecion2() {
		return dirrecion2;
	}

	public void setDirrecion2(String dirrecion2) {
		this.dirrecion2 = dirrecion2;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getCcv() {
		return ccv;
	}

	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	public LinkedList<Role> getRoles() {
		return roles;
	}

	public void setRoles(LinkedList<Role> roles) {
		this.roles = roles;
	}
	
}
