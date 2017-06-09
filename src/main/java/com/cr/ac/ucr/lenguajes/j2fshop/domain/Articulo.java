package com.cr.ac.ucr.lenguajes.j2fshop.domain;

public class Articulo {

private int cantidad;
private Producto producto;


public Articulo() {

}

public Articulo(int cantidad, Producto producto) {
	this.cantidad = cantidad;
	this.producto = producto;
}

public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}
public Producto getProducto() {
	return producto;
}
public void setProducto(Producto producto) {
	this.producto = producto;
}

@Override
public String toString() {
	return "Articulo [cantidad=" + cantidad + ", producto=" + producto + "]\n" ;
}

}

