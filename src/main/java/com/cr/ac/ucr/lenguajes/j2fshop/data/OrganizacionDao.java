package com.cr.ac.ucr.lenguajes.j2fshop.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.cr.ac.ucr.lenguajes.j2fshop.domain.Categoria;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Organizacion;
import com.cr.ac.ucr.lenguajes.j2fshop.form.CategoriaForm;
import com.cr.ac.ucr.lenguajes.j2fshop.form.OrganizacionForm;

@Repository
public class OrganizacionDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallEditarDatosOrganizacion;
	
	@Autowired
	public void setDataSource(DataSource dataSource){

		this.jdbcTemplate= new JdbcTemplate(dataSource);
		simpleJdbcCallEditarDatosOrganizacion = new SimpleJdbcCall(dataSource).withProcedureName("modificarDatosCompania");
		
	}
	
	public Organizacion obtenerDatos(){
		List<Organizacion> organizacion = new ArrayList<>();

		String sqlSelect = "select idDatosCompania,nombreCompania, descripcionCompania,ubicacionCompania,"
				+ " telefonoCompania,correoCompania,misionCompania,visionCompania "
				+ " from datosj2fshop;";
		
		jdbcTemplate.query(sqlSelect, new Object []{}, (rs, row)-> new Organizacion(rs.getInt("idDatosCompania"),
				rs.getString("nombreCompania"), rs.getString("descripcionCompania"), rs.getString("ubicacionCompania"), 
				rs.getString("telefonoCompania"), rs.getString("correoCompania"),rs.getString("misionCompania"),rs.getString("visionCompania")))
				.forEach(entry -> organizacion.add(entry));

		return organizacion.isEmpty()?null:organizacion.get(0);
	}
	
	public void modificarDatos(OrganizacionForm organizacionForm) throws SQLException{
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("_idCompania", organizacionForm.getIdOrganizacion())
				.addValue("_nombre", organizacionForm.getNombre())
				.addValue("_descripcion", organizacionForm.getDescripcion())
				.addValue("_ubicacion", organizacionForm.getUbicacion())
				.addValue("_telefono", organizacionForm.getTelefono())
				.addValue("_correo", organizacionForm.getCorreo())
				.addValue("_mision", organizacionForm.getMision())
				.addValue("_vision", organizacionForm.getVision());
		simpleJdbcCallEditarDatosOrganizacion.execute(sqlParameterSource);
	}
}
