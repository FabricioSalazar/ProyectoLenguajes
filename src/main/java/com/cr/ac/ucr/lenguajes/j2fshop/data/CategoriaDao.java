package com.cr.ac.ucr.lenguajes.j2fshop.data;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.sql.DataSource;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;


import com.cr.ac.ucr.lenguajes.j2fshop.domain.Categoria;
import com.cr.ac.ucr.lenguajes.j2fshop.form.CategoriaForm;



@Repository
public class CategoriaDao {


	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallEditarCategoria;
	private SimpleJdbcCall simpleJdbcCallInsertarCategoria;
	private SimpleJdbcCall simpleJdbcCallEliminarCategoria;
	
	@Autowired
	public void setDataSource(DataSource dataSource){

		this.jdbcTemplate= new JdbcTemplate(dataSource);
		simpleJdbcCallEditarCategoria = new SimpleJdbcCall(dataSource).withProcedureName("modificarCategoria");
		simpleJdbcCallEliminarCategoria = new SimpleJdbcCall(dataSource).withProcedureName("eliminarCategoria");
		simpleJdbcCallInsertarCategoria = new SimpleJdbcCall(dataSource).withProcedureName("insertarCategoria");
		
	}
	
	public List<Categoria> findAllCategories(){
		
		List<Categoria> categorias= new ArrayList<>();
		
		String sqlSelect="select c.idCategoria, c.nombreCategoria, c.imagenCategoria"
				+ " from categoria c ;";
		
		jdbcTemplate.query(sqlSelect, new Object []{}, (rs, row)-> new Categoria(rs.getInt("idCategoria"),
				rs.getString("nombreCategoria")))
				.forEach(entry -> categorias.add(entry));

		return categorias;
	}
	
	
	public List<Categoria> findCategoriaByNombre(String nombre){
		List<Categoria> categorias= new ArrayList<>();
		
		String sqlSelect="select c.idCategoria, c.nombreCategoria, c.imagenCategoria"
				+ " from categoria c "
				+ " where nombreCategoria like '%"+nombre+"%';";
		
		jdbcTemplate.query(sqlSelect, new Object []{}, (rs, row)-> new Categoria(rs.getInt("idCategoria"),
				rs.getString("nombreCategoria")))
				.forEach(entry -> categorias.add(entry));

		return categorias;
	}
	
	public Categoria findCategoriaByCode(int idCategoria){
		
		List<Categoria> categorias= new ArrayList<>();

		String sqlSelect="select c.idCategoria, c.nombreCategoria, c.imagenCategoria"
				+ " from categoria c "
				+ " where idCategoria="+idCategoria+";";
		
		jdbcTemplate.query(sqlSelect, new Object []{}, (rs, row)-> new Categoria(rs.getInt("idCategoria"),
				rs.getString("nombreCategoria")))
				.forEach(entry -> categorias.add(entry));

		return categorias.isEmpty()?null:categorias.get(0);
	}
	
	public void insertarCategoria(CategoriaForm categoriaForm) throws SQLException{
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("_nombre", categoriaForm.getNombre())
				.addValue("_imagen", categoriaForm.getImagen());
		
		simpleJdbcCallInsertarCategoria.execute(sqlParameterSource);
	}
	
	public void modificarCategoria(CategoriaForm categoriaForm) throws SQLException{
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("_idCategoria", categoriaForm.getIdCategoria())
				.addValue("_nombre", categoriaForm.getNombre())
				.addValue("_imagen", categoriaForm.getImagen());
		
		simpleJdbcCallEditarCategoria.execute(sqlParameterSource);
	}
	
	public void eliminarCategoria(int idCategoria) throws SQLException{
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("_idCategoria", idCategoria);
		
		simpleJdbcCallEliminarCategoria.execute(sqlParameterSource);
	}
	
	public void saveImageCategory(String ruta, int idCategoria){
		byte[] a= new byte[100000];
		try{
			FileInputStream f_in= new FileInputStream(ruta);
			a= org.apache.commons.io.IOUtils.toByteArray(f_in);
		}catch(Exception e){
			
		}
		
		String sqlInsert="insert into Categoria(imagenCategoria) values('"+a+"');";
		jdbcTemplate.execute(sqlInsert);
	}
	
	public ImageIcon obtenerImagen(java.sql.Blob blob) {
		ImageIcon imagen = null;
		// primero me aseguro que no este vacío.
		if (blob != null) {
			try {
				byte[] data = blob.getBytes(1, (int) blob.length());
				BufferedImage img = null;

				try {
					img = ImageIO.read(new ByteArrayInputStream(data));
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
				imagen = new ImageIcon(img);

			} catch (Exception ex) {
				// No hay imagen
			}
		} else {
			// No hay imagen
		}
		return imagen;
	}
	
	private static final class CategoriaExtractor implements ResultSetExtractor<List<Categoria>> {
		
		@Autowired
		private CategoriaDao categoriaDao;
		
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
					//categoria.setImagenCategoria(categoriaDao.obtenerImagen(rs.getBlob("imagenCategoria")));
				} // if
				categoria.toString();
			} // while
			return new ArrayList<Categoria>(map.values());

		} //extractData
		
	}//UsuarioExtractorr
}
