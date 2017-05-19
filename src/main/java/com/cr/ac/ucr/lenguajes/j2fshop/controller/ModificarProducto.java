package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cr.ac.ucr.lenguajes.j2fshop.business.ProductoService;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Producto;
import com.cr.ac.ucr.lenguajes.j2fshop.form.ProductoForm;

@Controller
public class ModificarProducto {

	@Autowired
	private ProductoService productoService;
	
	private Producto productoModificar;
	
	@RequestMapping(value = "/modificarProducto/{id}/**", method = RequestMethod.GET)
	public String showForm(ProductoForm productoForm, Model model, @PathVariable String id,
			HttpServletRequest request) {

		int idProducto = Integer
				.parseInt(new AntPathMatcher().extractPathWithinPattern("/{id}/**", request.getRequestURI()));

		productoModificar = productoService.findProductByCode(idProducto);
		
		
		productoForm.setIdProducto(productoModificar.getIdProducto());
		productoForm.setNombre(productoModificar.getNombre());
		productoForm.setDescripcion(productoModificar.getDescripcion());
		productoForm.setPrecio(productoModificar.getPrecio());
		productoForm.setUnidadesStock(productoModificar.getUnidadesStock());
		productoForm.setImpuesto(productoModificar.isImpuesto());
		productoForm.setPorcentajeImpuesto(productoModificar.getPorcentajeImpuesto());
		//productoForm.setImagen(productoModificar.getImagen());
		productoForm.setIDcategoria(productoModificar.getCategoria().getIdCategoria());
		model.addAttribute("productoForm", productoForm);

		return "/modificarProducto";
	}
	
	@RequestMapping(value="/modificarProducto/salvar", method=RequestMethod.POST)
	public String save(@Valid ProductoForm productoForm, BindingResult bindingResult, Model model, @RequestParam Map<String, String> requestParams){
		
		boolean insertado;
		
		if(bindingResult.hasErrors()){
			model.addAttribute("productoModificar", productoModificar);
			model.addAttribute("productoForm", productoForm);
			return "editarAutor";
		}else{
			try {
				productoService.editarProducto(productoForm);
				insertado=true;
			} catch (SQLException e) {
				insertado=false;
			}
		}
		
		if(insertado){
			model.addAttribute("mensaje", "El producto fue modificado con exito");
			return "success";
		}else{
			model.addAttribute("mensaje", "No se puedo modificar el autor");
			return "error";
		}
	}
}
