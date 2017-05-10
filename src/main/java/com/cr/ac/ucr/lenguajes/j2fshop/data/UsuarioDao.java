package com.cr.ac.ucr.lenguajes.j2fshop.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.cr.ac.ucr.lenguajes.j2fshop.domain.Usuario;



@Repository
public class UsuarioDao {

	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource datasource){
		this.datasource= datasource;
		this.jdbcTemplate= new JdbcTemplate(datasource);
	}
	
	public List<Usuario> findAllUsers(){
		String sqlSelect="select idUsuario, nombre, apellido from usuario limit 0,1000;";
		
		List<Usuario> usuarios = jdbcTemplate.query(sqlSelect, new UsuarioExtractor());
		
		return usuarios;
	}
	
	
	private static final class UsuarioExtractor implements ResultSetExtractor<List<Usuario>> {

		@Override
		public List<Usuario> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Integer, Usuario> map = new HashMap<Integer, Usuario>();
			Usuario usuario = null;
			while (rs.next()) {
				Integer idUsuario = rs.getInt("idUsuario");
				usuario = map.get(idUsuario);
				if (usuario == null) {
					usuario = new Usuario();
					usuario.setIdUsuario(idUsuario);
					usuario.setNombre(rs.getString("nombre"));
					usuario.setApellidos(rs.getString("apellido"));
					
					map.put(idUsuario, usuario);
				} // if
			} // while
			return new ArrayList<Usuario>(map.values());

		} //extractData
		
	}//AutorExtractorr
}
