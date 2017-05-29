package com.cr.ac.ucr.lenguajes.j2fshop.form;

import java.io.File;

import javax.validation.constraints.NotNull;

public class CategoriaForm {
	
	private int idCategoria;
	
	@NotNull
	private String nombre;
	
	private File imagen;

	public CategoriaForm() {
	}

	
	public int getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public File getImagen() {
		return imagen;
	}

	public void setImagen(File imagen) {
		this.imagen = imagen;
	}
	
	
	
	
}
