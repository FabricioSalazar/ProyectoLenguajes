package com.cr.ac.ucr.lenguajes.j2fshop.business;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.ac.ucr.lenguajes.j2fshop.data.ProductoDao;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Producto;
import com.cr.ac.ucr.lenguajes.j2fshop.form.ProductoForm;

@Service
public class ProductoService {

	@Autowired
	private ProductoDao productoDao;
	
	public void saveImageProduct(File imagen){
		productoDao.saveImageProduct(imagen);	
	}
	
	public List<Producto> findAllProducts(){
		return productoDao.findAllProducts();
	}
	
	public List<Producto> findProducts(String criterioBusqueda){
		return productoDao.findProducts(criterioBusqueda);
	}
	
	public List<Producto> findProductsByCategoria(String nombreCategoria){
		return productoDao.findProductsByCategoria(nombreCategoria);
	}
	
	public Producto findProductByCode(int idProducto){
		return productoDao.findProductByCode(idProducto);
	}
	
	public void editarProducto(ProductoForm productoForm) throws SQLException{
		productoDao.editarProducto(productoForm);
	}
	
	public int insertarProducto(ProductoForm productoForm) throws SQLException{
		return productoDao.insertarProducto(productoForm);
	}
	
	public void eliminarProducto(int idProducto) throws SQLException{
		productoDao.eliminarProducto(idProducto);
	}
	
	public void actualizaReporte(){
		productoDao.actualizaReporte();
	}
	
}
