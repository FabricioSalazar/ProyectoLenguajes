package com.cr.ac.ucr.lenguajes.j2fshop.domain;

import java.util.ArrayList;
import java.util.Iterator;

public class SessionUser {

	private ArrayList<Articulo> articulos;
	private Usuario user;

	public SessionUser(ArrayList<Articulo> articulos, Usuario user) {
		this.articulos = articulos;
		this.user = user;
	}

	public SessionUser() {
		articulos = new ArrayList<Articulo>();
		user = new Usuario();
	}

	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(ArrayList<Articulo> articulos) {
		this.articulos = articulos;

	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public float getPrecioTotal() {
		float totalPrecio = 0;
		Iterator<Articulo> iterator = articulos.iterator();
		while (iterator.hasNext()) {
			totalPrecio += iterator.next().getProducto().getPrecioTotal();
		}
		return totalPrecio;
	}
}
