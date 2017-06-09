package com.cr.ac.ucr.lenguajes.j2fshop.storage;

import com.cr.ac.ucr.lenguajes.j2fshop.domain.Articulo;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.SessionUser;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Usuario;

import java.util.ArrayList;
import java.util.HashMap;

public class SessionManager {

	public static HashMap<String, SessionUser> sesiones = new HashMap<>();

	public static void addProduct(String idSesion, Articulo product) {
		if (sesiones.get(idSesion) == null) {
			System.out.println(product.toString());
			ArrayList<Articulo> carrito = new ArrayList<Articulo>();
			carrito.add(product);
			sesiones.put(idSesion, new SessionUser(carrito, new Usuario()));
		} else {
			sesiones.get(idSesion).getArticulos().add(product);
		}
	}
	
	public static void setSession(String idSesion, Usuario user, ArrayList<Articulo> carrito){
		if (sesiones.get(idSesion) == null) {
			System.out.println(idSesion);
			System.out.println("INGRESANDO NUEVO");
			sesiones.put(idSesion, new SessionUser(carrito, user));
		} else {
			sesiones.get(idSesion).setUser(user);
		}
	}
	
	public static SessionUser getSession(String idSesion){
		return sesiones.get(idSesion);

	}
	
	public static void removeProductBySession(String idSesion, Articulo article) {
		sesiones.get(idSesion).getArticulos().remove(article);
	}

	public static void removeSessionByUser(String idSesion, Usuario user) {
		if (sesiones.get(idSesion).getUser() != null) {
			sesiones.remove(idSesion);
		}
	}

	public static ArrayList<Articulo> getCarBySessionId(String idSesion) {
		return sesiones.get(idSesion).getArticulos();
	}

	public static Usuario getUserBySessionId(String idSesion) {
		return sesiones.get(idSesion).getUser();
	}

	public static void setUserBySessionId(String idSesion, Usuario user) {
		sesiones.get(idSesion).setUser(user);
	}
	
}
