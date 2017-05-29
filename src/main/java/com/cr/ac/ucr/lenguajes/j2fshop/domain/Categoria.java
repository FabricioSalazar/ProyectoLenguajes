package com.cr.ac.ucr.lenguajes.j2fshop.domain;

import java.io.File;

public class Categoria {

	private int idCategoria;
	private String nombreCategoria;
	private File imagenCategoria;
	
	public Categoria() {
	}

	public Categoria(int idCategoria, String nombreCategoria) {
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public File getImagenCategoria() {
		return imagenCategoria;
	}

	public void setImagenCategoria(File imagenCategoria) {
		this.imagenCategoria = imagenCategoria;
	}
	
	
}
