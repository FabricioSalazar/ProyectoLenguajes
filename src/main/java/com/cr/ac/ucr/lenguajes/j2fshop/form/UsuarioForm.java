package com.cr.ac.ucr.lenguajes.j2fshop.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioForm {
	@NotNull
	private int idUsuario;
	private String nombre;
	private String apellidos;
	@NotNull
	@Size(min=10, max=255)
	private String login;
	@NotNull
	@Size(max=255)
	private String password;
	@NotNull
	@Size(max=255)
	private String direccion1;
	@NotNull
	@Size(max=255)
	private String direccion2;
	@NotNull
	@Size(max=255)
	private String pais;
	@NotNull
	@Size(max=255)
	private String ciudad;
	@NotNull
	@Size(max=255)
	private String estado;
	@NotNull
	@Size(max=50)
	private String codigoPostal;
	@NotNull
	@Size(max=15)
	private String telefono;
	@NotNull
	@Size(max=16)
	private String numeroTarjeta;
	@NotNull
	@Size(max=3)
	private String ccv;
	
	public UsuarioForm() {
		super();
	}

	public int getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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
	
	public String getDireccion1() {
		return direccion1;
	}
	
	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}
	
	public String getDireccion2() {
		return direccion2;
	}
	
	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
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
}
