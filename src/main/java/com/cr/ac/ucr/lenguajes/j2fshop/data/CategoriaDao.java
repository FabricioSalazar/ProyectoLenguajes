package com.cr.ac.ucr.lenguajes.j2fshop.data;

import java.io.FileInputStream;
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

import com.cr.ac.ucr.lenguajes.j2fshop.data.ProductoDao.ProductoExtractor;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Categoria;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Producto;

@Repository
public class CategoriaDao {

	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource datasource){
		this.datasource= datasource;
		this.jdbcTemplate= new JdbcTemplate(datasource);
	}
	
	public List<Categoria> findAllCategories(){
		String sqlSelect="select c.idCategoria, c.nombreCategoria, c.imagenCategoria"
				+ " from categoria c ";
		
		List<Categoria> categorias= jdbcTemplate.query(sqlSelect, new CategoriaExtractor());
		
		return categorias;
	}
	
	public void saveImageCategory(){
		byte[] a= new byte[100000];
		try{
			FileInputStream f_in= new FileInputStream("C:/Users/Usuario/Pictures/Saved Pictures/accesorios.jpg");
			a= org.apache.commons.io.IOUtils.toByteArray(f_in);
		}catch(Exception e){
			
		}
		
		String sqlInsert="insert into Categoria(imagenCategoria) values('"+a+"');";
		jdbcTemplate.execute(sqlInsert);
	}
	
	private static final class CategoriaExtractor implements ResultSetExtractor<List<Categoria>> {

		@Override
		public List<Categoria> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Integer, Categoria> map = new HashMap<Integer, Categoria>();
			Categoria categoria = null;
			while (rs.next()) {
				Integer idCategoria = rs.getInt("idCategoria");
				categoria = map.get(idCategoria);
				if (categoria == null) {
					categoria = new Categoria();
					categoria.setIdCategoria(idCategoria);
					categoria.setNombreCategoria(rs.getString("nombreCategoria"));
					categoria.setImagenCategoria(imagen);
				} // if

			} // while
			return new ArrayList<Categoria>(map.values());

		} //extractData
		
	}//UsuarioExtractorr
}
