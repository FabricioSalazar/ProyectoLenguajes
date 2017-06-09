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

import com.cr.ac.ucr.lenguajes.j2fshop.business.OrganizacionService;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Organizacion;
import com.cr.ac.ucr.lenguajes.j2fshop.form.OrganizacionForm;
import com.cr.ac.ucr.lenguajes.j2fshop.form.ProductoForm;

@Controller
public class MantenerOrganizacionController {

	@Autowired
	private OrganizacionService organizacionService;
	
	private Organizacion organizacion;
	
	// modificar un producto
		@RequestMapping(value = "admin/modificarOrganizacion", method = RequestMethod.GET)
		public String showFormModificarOrganizacion(OrganizacionForm organizacionForm, Model model) {


			organizacion = organizacionService.obtenerDatos();
			
			organizacionForm.setIdOrganizacion(organizacion.getIdDatos());
			organizacionForm.setNombre(organizacion.getNombre());
			organizacionForm.setDescripcion(organizacion.getDescripcion());
			organizacionForm.setUbicacion(organizacion.getUbicacion());
			organizacionForm.setCorreo(organizacion.getCorreo());
			organizacionForm.setMision(organizacion.getMision());
			organizacionForm.setTelefono(organizacion.getTelefono());
			organizacionForm.setVision(organizacion.getVision());
			
			model.addAttribute("organizacionForm", organizacionForm);
			
			return "modificarOrganizacion";
		}
		
		@RequestMapping(value="admin/modificarOrganizacion/salvar", method=RequestMethod.POST)
		public String saveModificarOrganizacion(@Valid OrganizacionForm organizacionForm, BindingResult bindingResult, Model model, @RequestParam Map<String, String> requestParams){
			
			boolean insertado;
			
			if(bindingResult.hasErrors()){
				model.addAttribute("organizacionForm", organizacionForm);
				return "modificarOrganizacion";
			}else{
				try {
					organizacionService.modificarDatos(organizacionForm);
					insertado=true;
				} catch (SQLException e) {
					insertado=false;
				}
			}
			
			if(insertado){
				model.addAttribute("msg", "Datos organizacionales modificados con exito");
				return "success";
			}else{
				return "error";
			}
		}
}
