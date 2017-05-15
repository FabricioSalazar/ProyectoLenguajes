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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cr.ac.ucr.lenguajes.j2fshop.domain.Role;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Usuario;



@Repository
public class UsuarioDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallSaveUsers;
	private float saldoDefault=500000;
	
	@Autowired
	public void setDataSource(DataSource datasource){
		this.jdbcTemplate= new JdbcTemplate(datasource);
		this.simpleJdbcCallSaveUsers = new SimpleJdbcCall(datasource).withProcedureName("InsertarUsuario");
	}
	
	public Usuario findUserByLogIn(String login) {
		String sqlSelect="select u.idUsuario, u.nombre, u.apellido, u.login, u.password, u.enabled, du.direccion1,"
				+ " du.direccion2, du.pais, du.ciudad, du.estado, du.codigoPostal, iu.telefono,iu.numeroTarjeta, "
				+ " iu.ccv, iu.saldo, r.idRole, r.role_Name"
				+ " from Usuario u left join DireccionUsuario as du on u.idDireccionUsuario=du.idDireccion"
				+ " left join InformacionUsuario iu on u.idInformacionUsuario= iu.idInformacionUsuario"
				+ " left join role_usuario ru on u.idUsuario= ru.idUsuario"
				+ " left join role r on r.idRole= ru.idRole where u.login = '"+login+"';";
		
		List<Usuario> usuarios = jdbcTemplate.query(sqlSelect, new UsuarioExtractor());
		
		return usuarios.isEmpty()?null:usuarios.get(0);
	}
	
	public List<Usuario> findAllUsers(){
		String sqlSelect="select u.idUsuario, u.nombre, u.apellido, u.login, u.password, u.enabled, du.direccion1,"
				+ " du.direccion2, du.pais, du.ciudad, du.estado, du.codigoPostal, iu.telefono,iu.numeroTarjeta, "
				+ " iu.ccv, iu.saldo, r.idRole, r.role_Name"
				+ " from Usuario u left join DireccionUsuario as du on u.idDireccionUsuario=du.idDireccion"
				+ " left join InformacionUsuario iu on u.idInformacionUsuario= iu.idInformacionUsuario"
				+ " left join role_usuario ru on u.idUsuario= ru.idUsuario"
				+ " left join role r on r.idRole= ru.idRole;";
		
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
					usuario.setLogin(rs.getString("login"));
					usuario.setPassword(rs.getString("password"));
					usuario.setEnabled(rs.getBoolean("enabled"));
					usuario.setDireccion1(rs.getString("direccion1"));
					usuario.setDireccion2(rs.getString("direccion2"));
					usuario.setPais(rs.getString("pais"));
					usuario.setCiudad(rs.getString("ciudad"));
					usuario.setEstado(rs.getString("estado"));
					usuario.setCodigoPostal(rs.getString("codigoPostal"));
					usuario.setTelefono(rs.getString("telefono"));
					usuario.setNumeroTarjeta(rs.getString("numeroTarjeta"));
					usuario.setCcv(rs.getString("ccv"));
					usuario.setSaldo(rs.getFloat("saldo"));

					map.put(idUsuario, usuario);
				} // if
				int idRole = rs.getInt("idRole");
				if (idRole > 0) {
					Role role= new Role();
					role.setIdRole(idRole);
					role.setRoleName(rs.getString("role_name"));
					usuario.getRoles().add(role);
				} // if
			} // while
			return new ArrayList<Usuario>(map.values());

		} //extractData
		
	}//UsuarioExtractor

	@Transactional
	public void save(Usuario user) {
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("_nombre", user.getNombre())
				.addValue("_apellido", user.getApellidos())
				.addValue("_login",user.getLogin())
				.addValue("_enabled", 1)
				.addValue("_password", user.getPassword())
				.addValue("_direccion1", user.getDireccion1())
				.addValue("_direccion2", user.getDireccion2())
				.addValue("_pais", user.getPais())
				.addValue("_ciudad", user.getCiudad())
				.addValue("_estado", user.getEstado())
				.addValue("_codigoPostal", user.getCodigoPostal())
				.addValue("_telefono", user.getTelefono())
				.addValue("_numeroTarjeta", user.getNumeroTarjeta())
				.addValue("_ccv", user.getCcv())
				.addValue("_saldo", this.saldoDefault);
		
		simpleJdbcCallSaveUsers.execute(parameterSource);
	}

	
}
