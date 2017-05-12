package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cr.ac.ucr.lenguajes.j2fshop.business.ProductoService;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Producto;

@Controller
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	private List<Producto> listaProductos;
	
	private PagedList paged;

	private int pagActual = 0;
	private int cantidad = 0;
	private final int ELEM_POR_PAG = 5; // elementos visibles por cada pagina
	
	@RequestMapping(value = "/catalogoProductos", method = RequestMethod.GET)
	public String iniciar(Model model){
		model.addAttribute("productos", listaProductos);
		
		return "catalogoProductos";
	}
	
	@RequestMapping(value="/catalogoProductos/buscar", method= RequestMethod.POST)
	public String buscar(@RequestParam Map<String, String> requestParams, Model model){
		String criterioBusqueda = requestParams.get("nombrePublicador");

		
		listaProductos = productoService.findProducts(criterioBusqueda.trim());
			
		pagActual = 0; // reinicia la pagina visible actual a 0
		paged = new PagedList(ELEM_POR_PAG, listaProductos);

		cantidad=listaProductos.size();
		
		model.addAttribute("cantidadProductos", cantidad);
		model.addAttribute("productos", paged.getPage(pagActual));
		model.addAttribute("pagActual", pagActual);
		model.addAttribute("totalPag", paged.totalPaginas-1);
		
		return "catalogoProductos";
	}
	
	
	@RequestMapping(value="/catalogoProductos/buscar/next")
	public String next(Model model){
		if (pagActual < paged.paginas.size() - 2) {
			pagActual++;
		}
		model.addAttribute("cantidadProductos", cantidad);
		model.addAttribute("productos", paged.getPage(pagActual));
		model.addAttribute("pagActual", pagActual);
		model.addAttribute("totalPag", paged.totalPaginas-1);
		return "catalogoProductos";
	}
	
	@RequestMapping(value="/catalogoProductos/buscar/prev")
	public String prev(Model model){
			if (pagActual != 0) {
				pagActual--;
			}
			model.addAttribute("cantidadProductos", cantidad);
			model.addAttribute("productos", paged.getPage(pagActual));
			model.addAttribute("pagActual", pagActual);
			model.addAttribute("totalPag", paged.totalPaginas-1);
		return "catalogoProductos";
	}
	
private class PagedList {
		
		// created by jumocrc
		
		private int CANT_ELM;
		private int totalPaginas;
		private List<List<Producto>> paginas;
		private List<Producto> productos;

		public PagedList(int CANT_ELM, List<Producto> productos) {
			this.CANT_ELM = CANT_ELM;
			this.productos= productos;
			paginas = new LinkedList<>();
			generatePages();

		} // cosntructor

		private void generatePages() {
			// el total de paginas es calculado con el modulo de la cantidad de
			// libros entre la cantidad de elementos visibles por pagina

			totalPaginas = (productos.size() % CANT_ELM == 0 ? productos.size() / CANT_ELM : productos.size() / CANT_ELM + 1);
			if (totalPaginas == 0) totalPaginas = 1;
			int contador = 0;
			for (int i = 0; i <= totalPaginas; i++) {
				List<Producto> productosActuales = new ArrayList<>();
				for (int k = 0; k < CANT_ELM; k++) {
					try {
						Producto aux = productos.get(contador);
						productosActuales.add(aux);
						contador++;
					} catch (IndexOutOfBoundsException e) {
						break;
					}
				}
				paginas.add(productosActuales);
			}
		}// generatePages()

		public List<Producto> getPage(int page) {
			return paginas.get(page);
		}// getPage()
	}
}
