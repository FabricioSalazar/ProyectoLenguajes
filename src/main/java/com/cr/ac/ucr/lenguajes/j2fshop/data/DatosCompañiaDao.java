package com.cr.ac.ucr.lenguajes.j2fshop.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.cr.ac.ucr.lenguajes.j2fshop.domain.Categoria;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.DatosCompania;

@Repository
public class DatosCompañiaDao {
	
	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource datasource){
		this.datasource= datasource;
		this.jdbcTemplate= new JdbcTemplate(datasource);
	}
	
	public DatosCompania findAllCompanyData(){
		List<DatosCompania> datos= new ArrayList<>();
		
		String sqlSelect="select idDatosCompania, nombreCompania, descripcionCompania,"
				+ " ubicacionCompania, telefonoCompania, correoCompania, misionCompania, visionCompania"
				+ " from DatosJ2FShop;";
		
		jdbcTemplate.query(sqlSelect, new Object []{}, (rs, row)-> new DatosCompania(rs.getInt("idDatosCompania"),
				rs.getString("nombreCompania"), rs.getString("descripcionCompania"), rs.getString("ubicacionCompania"),
				rs.getString("telefonoCompania"), rs.getString("correoCompania"), rs.getString("misionCompania"),
				rs.getString("visionCompania")))
				.forEach(entry -> datos.add(entry));

		return datos.isEmpty()?null:datos.get(0);
	}
}
