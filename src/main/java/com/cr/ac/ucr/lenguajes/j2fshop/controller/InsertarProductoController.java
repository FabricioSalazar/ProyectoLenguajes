package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cr.ac.ucr.lenguajes.j2fshop.data.ProductoDao;
import com.cr.ac.ucr.lenguajes.j2fshop.form.ProductoForm;
import com.cr.ac.ucr.lenguajes.j2fshop.storage.StorageService;

@Controller
public class InsertarProductoController {
	
	private final StorageService storageService;
	
	@Autowired
	public InsertarProductoController(StorageService storageService) {
	    this.storageService = storageService;
	}

	@RequestMapping("insertarProducto")
	public String iniciar(ProductoForm productoForm, Model model) {
		return "insertarProducto";
	}

	@RequestMapping(value = "/insertarProducto/salvar", method = RequestMethod.POST)
	public String save(@RequestParam Map<String, String> requestParams, @Valid ProductoForm producto,
		BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			
			return "insertarProducto";
		} else {
			
			System.out.println(producto.toString());
			
			//ProductoDao dao = new ProductoDao();
		//	dao.saveImageProduct(producto.getImagen());
			
			
			return "success";
		}

	}

}
