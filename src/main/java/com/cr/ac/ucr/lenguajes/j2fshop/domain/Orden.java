package com.cr.ac.ucr.lenguajes.j2fshop.domain;


import java.util.ArrayList;

public class Orden {

	private Usuario usuario;
	private ArrayList<Articulo> productos;

	private String trackNumber;

	private float totalCompra;
	private int idOrden;
	private int detalleOrden;
	
	public Orden(Usuario usuario, ArrayList<Articulo> productos, String trackNumber, float totalCompra, int idOrden, int detalleOrden) {		
		this.usuario = usuario;
		this.productos = productos;
		this.trackNumber = trackNumber;
		this.totalCompra = totalCompra;
		this.idOrden = idOrden;
		this.detalleOrden = detalleOrden;
	}
	
	public Orden(Usuario usuario, ArrayList<Articulo> productos, String trackNumber, float totalCompra) {		
		this.usuario = usuario;
		this.productos = productos;
		this.trackNumber = trackNumber;
		this.totalCompra = totalCompra;

	}
	
	
	public Orden() {		
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Articulo> getProducto() {
		return productos;
	}

	public void setProducto(ArrayList<Articulo> producto) {
		this.productos = producto;
	}

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	public float getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(float totalCompra) {
		this.totalCompra = totalCompra;
	}

	public int getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}

	public int getDetalleOrden() {
		return detalleOrden;
	}

	public void setDetalleOrden(int detalleOrden) {
		this.detalleOrden = detalleOrden;
	}
	
		
	/* IdUsuario
	 * Fecha orden y fecha de compra
	 * track number
	 * total precio total (calculo de prd*prd impuesto )
	 * direccion de envio
	 * idOrden (retorna el procedimiento)
	 * idProduto Lista
	 * idDetalleOrden (retorn el rocedimiento)
	 * canTidadProductos por id
	 * subTotal
	 * impuesto
	 * */
}
