package com.cr.ac.ucr.lenguajes.j2fshop.form;

import java.io.File;

import javax.validation.constraints.NotNull;


public class ProductoForm {

	private int idProducto;
	@NotNull
	private String nombre;
	@NotNull
	private String descripcion;
	@NotNull
	private float precio;
	@NotNull
	private int unidadesStock;
	
	private boolean impuesto;
	@NotNull
	private float porcentajeImpuesto;
	@NotNull
	private File imagen;
	@NotNull
	private int idCategoria;
	
	public ProductoForm() {
		
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

	public File getImagen() {
		return imagen;
	}

	public void setImagen(File imagen) {
		this.imagen = imagen;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Override
	public String toString() {
		return "ProductoForm [idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", unidadesStock=" + unidadesStock + ", impuesto=" + impuesto
				+ ", porcentajeImpuesto=" + porcentajeImpuesto + ", imagen=" + imagen.getPath()+ " "+imagen.getTotalSpace()+" "+imagen.length() + ", categoria=" + idCategoria
				+ "]";
	}
	
	
	
	
	
	
}
