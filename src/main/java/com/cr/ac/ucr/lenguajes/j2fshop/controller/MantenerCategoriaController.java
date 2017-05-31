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
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Categoria;
import com.cr.ac.ucr.lenguajes.j2fshop.form.CategoriaForm;

@Controller
public class MantenerCategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	private Categoria categoria;
	private List<Categoria> categorias;

	// Insertar Categoria
	@RequestMapping("insertarCategoria")
	public String iniciarInsertarProducto(CategoriaForm categoriaForm, Model model) {
		return "insertarCategoria";
	}

	@RequestMapping(value = "/insertarCategoria/salvar", method = RequestMethod.POST)
	public String saveInsertarProducto(@RequestParam Map<String, String> requestParams,
			@Valid CategoriaForm categoriaForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "insertarProducto";
		} else {

			try {
				categoriaService.insertarCategoria(categoriaForm);
			} catch (SQLException e) {
				return "error";
			}
			model.addAttribute("msg", "Categoria insertada con exito");
			return "success";
		}

	}

	// Modificar Categoria
	@RequestMapping(value = "/modificarCategoria/{id}/**", method = RequestMethod.GET)
	public String showFormModificarCategoria(CategoriaForm categoriaForm, Model model, @PathVariable String id,
			HttpServletRequest request) {

		int idCategoria = Integer
				.parseInt(new AntPathMatcher().extractPathWithinPattern("/{id}/**", request.getRequestURI()));

		categoria = categoriaService.findCategoriaByCode(idCategoria);

		categoriaForm.setIdCategoria(categoria.getIdCategoria());
		categoriaForm.setNombre(categoria.getNombreCategoria());
		// categoriaForm.setImagen(categoria.getImagenCategoria());

		model.addAttribute("categoriaForm", categoriaForm);

		return "modificarCategoria";
	}

	@RequestMapping(value = "/modificarCategoria/salvar", method = RequestMethod.POST)
	public String saveModificarCategoria(@Valid CategoriaForm categoriaForm, BindingResult bindingResult, Model model,
			@RequestParam Map<String, String> requestParams) {

		boolean modificado;

		if (bindingResult.hasErrors()) {
			model.addAttribute("categoriaForm", categoriaForm);
			return "modificarCategoria";
		} else {
			try {
				categoriaService.modificarCategoria(categoriaForm);
				modificado = true;
			} catch (SQLException e) {
				modificado = false;
			}
		}

		if (modificado) {
			model.addAttribute("msg", "Categoria modificada con exito");
			return "success";
		} else {
			return "error";
		}
	}
	
	// Eliminar Categoria
	@RequestMapping(value = "/borrarCategoria/{id}/**", method = RequestMethod.GET)
	public String removeCategoria(Model model, @PathVariable String id, HttpServletRequest request) {
		int idCategoria = Integer
				.parseInt(new AntPathMatcher().extractPathWithinPattern("/{id}/**", request.getRequestURI()));

		categoria = categoriaService.findCategoriaByCode(idCategoria);
		model.addAttribute("categoriaBorrar", categoria);
		return "borrarCategoria";
	}

	@RequestMapping(value = "/borrarCategoria/accept", method = RequestMethod.GET)
	public String accept(Model model) {

		try {
			categoriaService.eliminarCategoria(categoria.getIdCategoria());
		} catch (SQLException e) {
			return "error";
		}

		model.addAttribute("msg", "Categoria eliminada con exito");
		return "success";
	}

	// Obtener Categorias
	@RequestMapping(value = "/mantenerCategorias", method = RequestMethod.GET)
	public String iniciar(Model model) {
		model.addAttribute("categorias", categorias);
		return "mantenerCategorias";
	}

	@RequestMapping(value = "/mantenerCategorias/buscar", method = RequestMethod.POST)
	public String buscar(@RequestParam Map<String, String> requestParams, Model model) {
		String criterioBusqueda = requestParams.get("criterioBusqueda");

		categorias = categoriaService.findCategoriaByNombre(criterioBusqueda);
		model.addAttribute("categorias", categorias);
		return "mantenerCategorias";
	}
}
