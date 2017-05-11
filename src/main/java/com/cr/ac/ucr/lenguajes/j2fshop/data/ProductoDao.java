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

import com.cr.ac.ucr.lenguajes.j2fshop.domain.Categoria;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Producto;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Role;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Usuario;

@Repository
public class ProductoDao {

	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource datasource){
		this.datasource= datasource;
		this.jdbcTemplate= new JdbcTemplate(datasource);
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
	
	public void saveImageProduct(){
		byte[] a= new byte[100000];
		try{
			FileInputStream f_in= new FileInputStream("C:/Users/Usuario/Pictures/Saved Pictures/accesorios.jpg");
			a= org.apache.commons.io.IOUtils.toByteArray(f_in);
		}catch(Exception e){
			
		}
		
		String sqlInsert="insert into imagenProducto(imagen) values('"+a+"');";
		jdbcTemplate.execute(sqlInsert);
	}
	
	private static final class ProductoExtractor implements ResultSetExtractor<List<Producto>> {

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
					producto.setImagen(imagen);
				} // if
				int idCategoria = rs.getInt("idCategoria");
				if (idCategoria > 0) {
					Categoria categoria= new Categoria();
					categoria.setIdCategoria(idCategoria);
					categoria.setNombreCategoria(rs.getString("nombreCategoria"));
					categoria.setImagenCategoria(imagenCategoria);
					producto.getCategorias().add(categoria);
				} // if
			} // while
			return new ArrayList<Producto>(map.values());

		} //extractData
		
	}//ProductoExtractor
}
