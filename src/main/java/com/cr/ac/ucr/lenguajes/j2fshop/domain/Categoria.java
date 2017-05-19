package com.cr.ac.ucr.lenguajes.j2fshop.domain;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Categoria {

	private int idCategoria;
	private String nombreCategoria;
	private ImageIcon imagenCategoria;
	
	public Categoria() {
	}
	
	

	public Categoria(int idCategoria, String nombreCategoria) {
		super();
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

	public ImageIcon getImagenCategoria() {
		return imagenCategoria;
	}

	public void setImagenCategoria(ImageIcon imagenCategoria) {
		this.imagenCategoria = imagenCategoria;
	}
	
	
}
