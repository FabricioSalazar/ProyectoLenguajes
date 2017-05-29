package com.cr.ac.ucr.lenguajes.j2fshop.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.ac.ucr.lenguajes.j2fshop.data.CategoriaDao;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Categoria;
import com.cr.ac.ucr.lenguajes.j2fshop.form.CategoriaForm;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaDao categoriaDao;
	
	public List<Categoria> findAllCategories(){
		return categoriaDao.findAllCategories();
	}
	
	public List<Categoria> findCategoriaByNombre(String nombre){
		return categoriaDao.findCategoriaByNombre(nombre);
	}
	
	public Categoria findCategoriaByCode(int idCategoria){
		return categoriaDao.findCategoriaByCode(idCategoria);
	}
	
	public void insertarCategoria(CategoriaForm categoriaForm) throws SQLException{
		categoriaDao.insertarCategoria(categoriaForm);
	}
	
	public void modificarCategoria(CategoriaForm categoriaForm) throws SQLException{
		categoriaDao.modificarCategoria(categoriaForm);
	}
	
	public void eliminarCategoria(int idCategoria) throws SQLException{
		categoriaDao.eliminarCategoria(idCategoria);
	}
}
