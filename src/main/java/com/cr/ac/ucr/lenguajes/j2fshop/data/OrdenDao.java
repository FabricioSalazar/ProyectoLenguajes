package com.cr.ac.ucr.lenguajes.j2fshop.data;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import org.exolab.castor.mapping.xml.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cr.ac.ucr.lenguajes.j2fshop.domain.Orden;
import com.cr.ac.ucr.lenguajes.j2fshop.form.ProductoForm;

@Repository
public class OrdenDao {

	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallInsertarOrden;
	private SimpleJdbcCall simpleJdbcCallInsertarDetalleOrden;
	private SimpleJdbcCall simpleJdbcCallInsertarProductosDetallesOrden;
	
	@Autowired
	public void setDataSource(DataSource datasource){
		this.datasource= datasource;
		this.jdbcTemplate= new JdbcTemplate(datasource);
		this.simpleJdbcCallInsertarOrden = new SimpleJdbcCall(datasource).withProcedureName("insertarOrden")
				.declareParameters(new SqlOutParameter("_idOrden", Types.INTEGER))
				.declareParameters(new SqlParameter("_idUsuario", Types.INTEGER))
				.declareParameters(new SqlParameter("_trackNumber", Types.VARCHAR))
				.declareParameters(new SqlParameter("_total", Types.FLOAT))
				.declareParameters(new SqlParameter("_direccionEnvio", Types.VARCHAR))
				.declareParameters(new SqlParameter("_porcentajeImpuesto", Types.FLOAT));
		
		this.simpleJdbcCallInsertarDetalleOrden = new SimpleJdbcCall(datasource).withProcedureName("insertarDetalleOrden")
				.declareParameters(new SqlOutParameter("_idDetallaOrden", Types.INTEGER))
				.declareParameters(new SqlParameter("_idOrden", Types.INTEGER));
				
		this.simpleJdbcCallInsertarProductosDetallesOrden = new SimpleJdbcCall(datasource).withProcedureName("insertarProductosDetallesOrden")
				.declareParameters(new SqlParameter("_idProducto", Types.INTEGER))
				.declareParameters(new SqlParameter("_idDetalleOrden", Types.INTEGER))
				.declareParameters(new SqlParameter("_cantidad", Types.INTEGER))
				.declareParameters(new SqlParameter("_subTotal", Types.FLOAT))
				.declareParameters(new SqlParameter("_impuesto", Types.FLOAT));
	}
	
	@Transactional
	public void insertarOrden(Orden orden) throws SQLException {
		SqlParameterSource sqlParameterSourceOrden = new MapSqlParameterSource()
				.addValue("_idUsuario", orden.getUsuario().getIdUsuario())
				.addValue("_trackNumber", orden.getTrackNumber())
				.addValue("_total", orden.getTotalCompra())
				.addValue("_direccionEnvio", 13)
				.addValue("_porcentajeImpuesto", orden.getDireccion());
		
		Map<String, Object> outParametersOrden = simpleJdbcCallInsertarOrden.execute(sqlParameterSourceOrden);
		
		orden.setIdOrden(Integer.parseInt(outParametersOrden.get("_idOrden").toString()));
		
		SqlParameterSource sqlParameterSourceDetalleOrden = new MapSqlParameterSource()
				.addValue("_idOrden", orden.getIdOrden());
		
		Map<String, Object> outParametersDetalleOrden = simpleJdbcCallInsertarDetalleOrden.execute(sqlParameterSourceDetalleOrden);
		
		int detalleOrden = (Integer.parseInt(outParametersDetalleOrden.get("_idDetalleOrden").toString()));
		
		for (int i = 1; i <= orden.getProductos().size(); i++) {
			SqlParameterSource parameterSource = new MapSqlParameterSource()
					.addValue("_idProducto", orden.getProductos().get(i).getProducto().getIdProducto())
					.addValue("_idDetalleOrden", detalleOrden)
					.addValue("_cantidad", orden.getProductos().get(i).getCantidad())
					.addValue("_subTotal", orden.getProductos().get(i).getSubtotal())
					.addValue("_impuesto", orden.getProductos().get(i).getProducto().getPorcentajeImpuesto());
			simpleJdbcCallInsertarProductosDetallesOrden.execute(parameterSource);
		}

	}
}
