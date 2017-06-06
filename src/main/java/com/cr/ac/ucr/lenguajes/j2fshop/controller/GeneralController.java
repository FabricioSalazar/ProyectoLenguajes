package com.cr.ac.ucr.lenguajes.j2fshop.controller;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.cr.ac.ucr.lenguajes.j2fshop.business.UsuarioService;
import com.cr.ac.ucr.lenguajes.j2fshop.domain.Usuario;
/*
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
*/
@Controller
public class GeneralController {
	@Autowired
	DataSource dataSource;
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value = {"/", "/welcome", "/index"}, method = RequestMethod.GET)
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for(Object autho : auth.getAuthorities().toArray()){
			System.out.println(autho.toString());
			if (autho.toString().equals("Administrador")){
				model.addAttribute("admin",true);
			}
		}
		if(!auth.getName().equals("anonymousUser")) {
			model.addAttribute("usuario",auth.getName());
			Usuario user = usuarioService.findUserByLogIn(auth.getName());
			if (!user.isEnabled()) {
				usuarioService.enable(auth.getName());
			}
		} 
			
		return "header";
	}
	
	@RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
	public String admin(){
		return "headerAdmin";
	}
	
	/*
	@RequestMapping(path = "/pdfTotalVentas", method = RequestMethod.GET)
    public @ResponseBody void report(HttpServletResponse response) {
    	try {
    		InputStream is = this.getClass().getResourceAsStream("/reports/TotalVentas.jrxml");
    	
			JasperDesign design = JRXmlLoader.load(is);
			
			JasperReport report = JasperCompileManager.compileReport(design);
			
			Map<String,Object> parameterMap = new HashMap();
			
			parameterMap.put("datasource", dataSource.getConnection());
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameterMap, dataSource.getConnection());
			
			response.setContentType("application/x-pdf");
			response.setHeader("Content-Disposition", "inline: filename=totalventas.pdf");
			
			final OutputStream os = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, os);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
	@RequestMapping(path = "/pdfProductosVendidos", method = RequestMethod.GET)
    public @ResponseBody void reportProduct(HttpServletResponse response) {
    	try {
    		InputStream is = this.getClass().getResourceAsStream("/reports/ProductosMasVendidos.jrxml");
    	
			JasperDesign design = JRXmlLoader.load(is);
			
			JasperReport report = JasperCompileManager.compileReport(design);
			
			Map<String,Object> parameterMap = new HashMap();
			
			parameterMap.put("datasource", dataSource.getConnection());
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameterMap, dataSource.getConnection());
			
			response.setContentType("application/x-pdf");
			response.setHeader("Content-Disposition", "inline: filename=totalventas.pdf");
			
			final OutputStream os = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, os);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    */
}
