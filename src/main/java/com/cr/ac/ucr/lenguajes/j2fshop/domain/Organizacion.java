package com.cr.ac.ucr.lenguajes.j2fshop.domain;

public class Organizacion {
	
	private int idDatos;
	private String nombre;
	private String descripcion;
	private String ubicacion;
	private String telefono;
	private String correo;
	private String mision;
	private String vision;
	
	public Organizacion(int idDatos, String nombre, String descripcion, String ubicacion, String telefono,
			String correo, String mision, String vision) {
		super();
		this.idDatos = idDatos;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ubicacion = ubicacion;
		this.telefono = telefono;
		this.correo = correo;
		this.mision = mision;
		this.vision = vision;
	}

	public Organizacion() {
		super();
	}

	public int getIdDatos() {
		return idDatos;
	}

	public void setIdDatos(int idDatos) {
		this.idDatos = idDatos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getMision() {
		return mision;
	}

	public void setMision(String mision) {
		this.mision = mision;
	}

	public String getVision() {
		return vision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}
	
	

}


