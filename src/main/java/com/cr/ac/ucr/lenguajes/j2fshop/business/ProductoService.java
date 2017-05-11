package com.cr.ac.ucr.lenguajes.j2fshop.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.ac.ucr.lenguajes.j2fshop.data.ProductoDao;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Producto;

@Service
public class ProductoService {

	@Autowired
	private ProductoDao productoDao;
	
	
	public List<Producto> findAllProducts(){
		return productoDao.findAllProducts();
	}
}
