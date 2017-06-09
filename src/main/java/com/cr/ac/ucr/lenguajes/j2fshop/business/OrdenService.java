package com.cr.ac.ucr.lenguajes.j2fshop.business;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.ac.ucr.lenguajes.j2fshop.data.OrdenDao;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Orden;

@Service
public class OrdenService {

	@Autowired
	private OrdenDao ordenDao;
	
	public void insertarOrden(Orden orden) throws SQLException {
		ordenDao.insertarOrden(orden);
	}
}
