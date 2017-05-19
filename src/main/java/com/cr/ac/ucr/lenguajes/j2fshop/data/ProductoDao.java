package com.cr.ac.ucr.lenguajes.j2fshop.data;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
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
import org.springframework.transaction.annotation.Transactional;

import com.cr.ac.ucr.lenguajes.j2fshop.domain.Categoria;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Producto;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Role;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Usuario;
import com.cr.ac.ucr.lenguajes.j2fshop.form.ProductoForm;
import com.mysql.jdbc.Blob;

@Repository
public class ProductoDao {

	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallEditarProducto;
	private SimpleJdbcCall simpleJdbcCallInsertarProducto;
	private SimpleJdbcCall simpleJdbcCallEliminarProducto;
	
	@Autowired
	public void setDataSource(DataSource datasource){
		this.datasource= datasource;
		this.jdbcTemplate= new JdbcTemplate(datasource);
		this.simpleJdbcCallEditarProducto = new SimpleJdbcCall(datasource).withProcedureName("modificarProducto");
		this.simpleJdbcCallEliminarProducto = new SimpleJdbcCall(datasource).withProcedureName("eliminarProducto");
		this.simpleJdbcCallInsertarProducto = new SimpleJdbcCall(datasource).withProcedureName("insertarProducto");
	}
	
	public List<Producto> findAllProducts(){
		String sqlSelect="select p.idProducto,p.nombre, p.descripcion, p.precio, p.unidadesStock, p.impuesto, p.porcentajeImpuesto,"
				+ " ip.imagen, c.idCategoria, c.nombreCategoria, c.imagenCategoria"
				+ " from Producto p left join imagenproducto ip on p.idImagenProducto=ip.idImagenProducto"
				+ " left join categoria_producto cp on p.idProducto=cp.idProducto "
				+ " left join categoria c on cp.idCategoria= c.idCategoria;";
		
		List<Producto> productos= jdbcTemplate.query(sqlSelect, new ProductoExtractor());
		
		return productos;
	}
	
	public List<Producto> findProducts(String criterioBusqueda){
		System.out.println(criterioBusqueda);
		String sqlSelect = "select p.idProducto,p.nombre, p.descripcion, p.precio, p.unidadesStock, p.impuesto, p.porcentajeImpuesto,"
				+ " ip.imagen, c.idCategoria, c.nombreCategoria, c.imagenCategoria"
				+ " from Producto p left join imagenproducto ip on p.idImagenProducto=ip.idImagenProducto"
				+ " left join categoria_producto cp on p.idProducto=cp.idProducto "
				+ " left join categoria c on cp.idCategoria= c.idCategoria" 
				+ " where p.nombre like '%"+criterioBusqueda + "%' or p.descripcion like '%" + criterioBusqueda + "%' or c.nombreCategoria like '%"+criterioBusqueda+"%';";

		List<Producto> productos = jdbcTemplate.query(sqlSelect, new ProductoExtractor());

		return productos;
	}
	
	public List<Producto> findProductsByCategoria(String nombreCategoria){

		String sqlSelect = "select p.idProducto,p.nombre, p.descripcion, p.precio, p.unidadesStock, p.impuesto, p.porcentajeImpuesto,"
				+ " ip.imagen, c.idCategoria, c.nombreCategoria, c.imagenCategoria"
				+ " from Producto p left join imagenproducto ip on p.idImagenProducto=ip.idImagenProducto"
				+ " left join categoria_producto cp on p.idProducto=cp.idProducto "
				+ " left join categoria c on cp.idCategoria= c.idCategoria" 
				+ " where c.nombreCategoria = '"+nombreCategoria + "';";

		List<Producto> productos = jdbcTemplate.query(sqlSelect, new ProductoExtractor());

		return productos;
	}
	
	public Producto findProductByCode(int idProducto){
		
		String sqlSelect = "select p.idProducto,p.nombre, p.descripcion, p.precio, p.unidadesStock, p.impuesto, p.porcentajeImpuesto,"
				+ " ip.imagen, c.idCategoria, c.nombreCategoria, c.imagenCategoria"
				+ " from Producto p left join imagenproducto ip on p.idImagenProducto=ip.idImagenProducto"
				+ " left join categoria_producto cp on p.idProducto=cp.idProducto "
				+ " left join categoria c on cp.idCategoria= c.idCategoria" 
				+ " where p.idProducto = "+idProducto+";";

		List<Producto> productos = jdbcTemplate.query(sqlSelect, new ProductoExtractor());
		
		return productos.isEmpty()?null:productos.get(0);
	}
	
	public void editarProducto(ProductoForm productoForm) throws SQLException{
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("_idProducto", productoForm.getIdProducto())
				.addValue("_nombre", productoForm.getNombre())
				.addValue("_descripcion",productoForm.getDescripcion())
				.addValue("_precio",productoForm.getPrecio())
				.addValue("_unidadesStock", productoForm.getUnidadesStock())
				.addValue("porcentajeImpuesto", productoForm.getPorcentajeImpuesto())
				.addValue("_imagen", productoForm.getImagen())
				.addValue("_idCategoria", productoForm.getIdCategoria());
		
		simpleJdbcCallEditarProducto.execute(sqlParameterSource);
	}
	
	@Transactional
	public void insertarProducto(ProductoForm productoForm) throws SQLException{
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("_nombre", productoForm.getNombre())
				.addValue("_descripcion",productoForm.getDescripcion())
				.addValue("_precio",productoForm.getPrecio())
				.addValue("_unidadesStock", productoForm.getUnidadesStock())
				.addValue("impuesto", true)
				.addValue("porcentajeImpuesto", productoForm.getPorcentajeImpuesto())
				.addValue("_imagen", productoForm.getImagen())
				.addValue("_idCategoria", productoForm.getIdCategoria());
		
		simpleJdbcCallInsertarProducto.execute(sqlParameterSource);
	}
	
	@Transactional
	public void eliminarProducto(int idProducto) throws SQLException{
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
				.addValue("_idProducto", idProducto);
		
		simpleJdbcCallEliminarProducto.execute(sqlParameterSource);
	}
	
	public void saveImageProduct(File image){
		byte[] a= new byte[100000];
		try{
			FileInputStream f_in= new FileInputStream(image);
			a= org.apache.commons.io.IOUtils.toByteArray(f_in);
			for(int i =0; i<a.length;i++){
				System.out.println(a[i]);
			}
		}catch(Exception e){
			
		}
		
		String sqlInsert="insert into imagenProducto(imagen) values('"+a+"');";
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
	
	private static final class ProductoExtractor implements ResultSetExtractor<List<Producto>> {
		
		@Autowired
		private ProductoDao productoDao;
		
		@Override
		public List<Producto> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Integer, Producto> map = new HashMap<Integer, Producto>();
			Producto producto = null;
			while (rs.next()) {
				Integer idProducto = rs.getInt("idProducto");
				producto = map.get(idProducto);
				if (producto == null) {
					producto = new Producto();
					producto.setIdProducto(idProducto);
					producto.setNombre(rs.getString("nombre"));
					producto.setDescripcion(rs.getString("descripcion"));
					producto.setPrecio(rs.getFloat("precio"));
					producto.setUnidadesStock(rs.getInt("unidadesStock"));
					producto.setImpuesto(rs.getBoolean("impuesto"));
					producto.setPorcentajeImpuesto(rs.getFloat("porcentajeImpuesto"));
					//producto.setImagen(productoDao.obtenerImagen(rs.getBlob("imagen")));
					producto.getCategoria().setIdCategoria(rs.getInt("idCategoria"));
					producto.getCategoria().setNombreCategoria(rs.getString("nombreCategoria"));
					//producto.getCategoria().setImagenCategoria(productoDao.obtenerImagen(rs.getBlob("imagenCategoria")));
					
					map.put(idProducto, producto);
				}
			} // while
			return new ArrayList<Producto>(map.values());

		} //extractData
		
	}//ProductoExtractor
}
