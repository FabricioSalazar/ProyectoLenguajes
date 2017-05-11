package com.cr.ac.ucr.lenguajes.j2fshop.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.ac.ucr.lenguajes.j2fshop.data.CategoriaDao;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Categoria;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaDao categoriaDao;
	
	public List<Categoria> findAllCategories(){
		return categoriaDao.findAllCategories();
	}
}
