package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import java.sql.SQLException;
import java.util.List;
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

import com.cr.ac.ucr.lenguajes.j2fshop.business.CategoriaService;
import com.cr.ac.ucr.lenguajes.j2fshop.business.ProductoService;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Producto;
import com.cr.ac.ucr.lenguajes.j2fshop.form.ProductoForm;

@Controller
public class MantenerProductoController {

	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private ProductoService productoService;
	
	private Producto producto;
	private List<Producto> productos;
	//insertar un producto
	
	@RequestMapping("insertarProducto")
	public String iniciarInsertarProducto(ProductoForm productoForm, Model model) {
		model.addAttribute("categorias", categoriaService.findAllCategories());
		return "insertarProducto";
	}

	@RequestMapping(value = "/insertarProducto/salvar", method = RequestMethod.POST)
	public String saveInsertarProducto(@RequestParam Map<String, String> requestParams, @Valid ProductoForm producto,
		BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("categorias", categoriaService.findAllCategories());
			return "insertarProducto";
		} else {
			
			System.out.println(producto.toString());
			
			//ProductoDao dao = new ProductoDao();
		//	dao.saveImageProduct(producto.getImagen());
			
			
			return "success";
		}

	}
	
	
	// modificar un producto
	@RequestMapping(value = "/modificarProducto/{id}/**", method = RequestMethod.GET)
	public String showFormModificarProducto(ProductoForm productoForm, Model model, @PathVariable String id,
			HttpServletRequest request) {

		int idProducto = Integer
				.parseInt(new AntPathMatcher().extractPathWithinPattern("/{id}/**", request.getRequestURI()));

		producto = productoService.findProductByCode(idProducto);
		
		
		productoForm.setIdProducto(producto.getIdProducto());
		productoForm.setNombre(producto.getNombre());
		productoForm.setDescripcion(producto.getDescripcion());
		productoForm.setPrecio(producto.getPrecio());
		productoForm.setUnidadesStock(producto.getUnidadesStock());
		productoForm.setImpuesto(producto.isImpuesto());
		productoForm.setPorcentajeImpuesto(producto.getPorcentajeImpuesto());
		//productoForm.setImagen(producto.getImagen());
		productoForm.setIDcategoria(producto.getCategoria().getIdCategoria());
		
		model.addAttribute("productoForm", productoForm);
		model.addAttribute("categorias", categoriaService.findAllCategories());
		
		return "modificarProducto";
	}
	
	@RequestMapping(value="/modificarProducto/salvar", method=RequestMethod.POST)
	public String saveModificarProducto(@Valid ProductoForm productoForm, BindingResult bindingResult, Model model, @RequestParam Map<String, String> requestParams){
		
		boolean insertado;
		
		if(bindingResult.hasErrors()){
			model.addAttribute("categorias", categoriaService.findAllCategories());
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
	
	
	//eliminar un producto
	@RequestMapping(value="/borrarProducto/{id}/**", method = RequestMethod.GET)
	public String remove(Model model, @PathVariable String id, HttpServletRequest request){
		int idProducto = Integer
				.parseInt(new AntPathMatcher().extractPathWithinPattern("/{id}/**", request.getRequestURI()));

		producto = productoService.findProductByCode(idProducto);
		model.addAttribute("productoBorrar", producto);
		return "borrarProducto";
	}
	
	@RequestMapping(value="/borrarProducto/accept", method = RequestMethod.GET)
	public String accept(Model model){
		
		try {
			productoService.eliminarProducto(producto.getIdProducto());
		} catch (SQLException e) {
			model.addAttribute("mensaje", "Ocurrio un error a la hora de borrar");
			return "error";
		}
		
		model.addAttribute("mensaje", "El producto fue borrado con exito");
		return "succes";
	}
	
	//obtener productos
	
	@RequestMapping(value="/mantenerProductos", method = RequestMethod.GET)
	public String iniciar(Model model){
		model.addAttribute("productos", productos);
		return "mantenerProductos";
	}
	
	@RequestMapping(value="/mantenerProductos/buscar", method= RequestMethod.POST)
	public String buscar(@RequestParam Map<String, String> requestParams, Model model){
		String criterioBusqueda = requestParams.get("criterioBusqueda");
		
		
		productos = productoService.findProducts(criterioBusqueda);
		model.addAttribute("productos", productos);

		return "mantenerProductos";
	}
}
