package com.cr.ac.ucr.lenguajes.j2fshop.business;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.ac.ucr.lenguajes.j2fshop.data.OrganizacionDao;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Organizacion;
import com.cr.ac.ucr.lenguajes.j2fshop.form.OrganizacionForm;

@Service
public class OrganizacionService {
	@Autowired
	private OrganizacionDao organizacionDao;
	
	public Organizacion obtenerDatos(){
		return organizacionDao.obtenerDatos();
	}
	
	public void modificarDatos(OrganizacionForm organizacionForm) throws SQLException{
		organizacionDao.modificarDatos(organizacionForm);
	}
}
