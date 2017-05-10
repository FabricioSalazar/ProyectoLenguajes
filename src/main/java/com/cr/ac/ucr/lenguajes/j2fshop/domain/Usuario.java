package com.cr.ac.ucr.lenguajes.j2fshop.domain;

public class Usuario {

	private int idUsuario;
	private String nombre;
	private String apellidos;
	
	
	public Usuario() {
	}
	
	public Usuario(int idUsuario, String nombre, String apellidos) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
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
	
	
	
}
