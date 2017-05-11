package com.cr.ac.ucr.lenguajes.j2fshop.domain;

import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Producto {

	private int idProducto;
	private String nombre;
	private String descripcion;
	private float precio;
	private int unidadesStock;
	private boolean impuesto;
	private float porcentajeImpuesto;
	private ImageIcon imagen;
	private LinkedList<Categoria> categorias;
	
	public Producto() {
		this.categorias= new LinkedList<>();
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getUnidadesStock() {
		return unidadesStock;
	}

	public void setUnidadesStock(int unidadesStock) {
		this.unidadesStock = unidadesStock;
	}

	public boolean isImpuesto() {
		return impuesto;
	}

	public void setImpuesto(boolean impuesto) {
		this.impuesto = impuesto;
	}

	public float getPorcentajeImpuesto() {
		return porcentajeImpuesto;
	}

	public void setPorcentajeImpuesto(float porcentajeImpuesto) {
		this.porcentajeImpuesto = porcentajeImpuesto;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}

	public LinkedList<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(LinkedList<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	
	
	
	
}
