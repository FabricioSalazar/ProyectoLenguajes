package com.cr.ac.ucr.lenguajes.j2fshop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cr.ac.ucr.lenguajes.j2fshop.business.ProductoService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

@Controller
public class ReportesController {
	@Autowired
	DataSource dataSource;
	@Autowired
	ProductoService productoService;
	
	//Users report PDF
	@RequestMapping(path = "/admin/reportes/reporteUsuariosPdf", method = RequestMethod.GET)
	public @ResponseBody void reportUsers(HttpServletResponse response){
		try{
			Connection con = dataSource.getConnection();
            //Load jrxml file
            InputStream fis = new FileInputStream(new File("src/main/resources/reports/users.jrxml"));
            
            //Compile jrxml file
            JasperDesign design = JRXmlLoader.load(fis);
            JasperReport report = JasperCompileManager.compileReport(design);
            
            //Create jasper print object
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, con);
            
            //Export the report
            //OutputStream os = new FileOutputStream(new File("/Users/Fabricio/Desktop/employee.pdf"));
            OutputStream os = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, os);
            
        }catch(Exception e){
            System.out.println(e);
        }
	}
	//Users report xlsx
	@RequestMapping(path = "/admin/reportes/reporteUsuariosXlsx", method = RequestMethod.GET)
	public @ResponseBody void reportUsersXlsx(HttpServletResponse response){
		try{
			Connection con = dataSource.getConnection();
            //Load jrxml file
            InputStream fis = new FileInputStream(new File("src/main/resources/reports/users.jrxml"));
            
            //Compile jrxml file
            JasperDesign design = JRXmlLoader.load(fis);
            JasperReport report = JasperCompileManager.compileReport(design);
            
            //Create jasper print object
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, con);
            
            //Export the report
            OutputStream os = response.getOutputStream();
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("reporteUsuarios.xlsx"));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            
            exporter.exportReport();
        }catch(Exception e){
            System.out.println(e);
        }
	}
	//Sell products pdf
	@RequestMapping(path = "/admin/reportes/productosMasVendidosPdf", method = RequestMethod.GET)
	public @ResponseBody void reportProducts(HttpServletResponse response){
		try{
			productoService.actualizaReporte();
			
			Connection con = dataSource.getConnection();
            //Load jrxml file
            InputStream fis = new FileInputStream(new File("src/main/resources/reports/productosMasVendidosMesActual.jrxml"));
            
            //Compile jrxml file
            JasperDesign design = JRXmlLoader.load(fis);
            JasperReport report = JasperCompileManager.compileReport(design);
            
            //Create jasper print object
            Map<String,String> map = new HashMap<>();
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, con);
            
            //Export the report
            OutputStream os = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, os);
            
        }catch(Exception e){
            System.out.println(e);
        }
	}
	//Sell products xlsx
	@RequestMapping(path = "/admin/reportes/productosMasVendidosXlsx", method = RequestMethod.GET)
	public @ResponseBody void reportProductsXlsx(HttpServletResponse response){
		try{
			productoService.actualizaReporte();
			
			Connection con = dataSource.getConnection();
            //Load jrxml file
            InputStream fis = new FileInputStream(new File("src/main/resources/reports/productosMasVendidosMesActual.jrxml"));
            
            //Compile jrxml file
            JasperDesign design = JRXmlLoader.load(fis);
            JasperReport report = JasperCompileManager.compileReport(design);
            
            //Create jasper print object
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, con);
            
            //Export the report
            OutputStream os = response.getOutputStream();
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("productosMasVendidosMesActual.xlsx"));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            
            exporter.exportReport();
            
        }catch(Exception e){
            System.out.println(e);
        }
	}
	
	//Sell products pdf
		@RequestMapping(path = "/admin/reportes/totalVentasPdf", method = RequestMethod.GET)
		public @ResponseBody void report(HttpServletResponse response){
			try{
				
				Connection con = dataSource.getConnection();
	            //Load jrxml file
	            InputStream fis = new FileInputStream(new File("src/main/resources/reports/TotalVentas.jrxml"));
	            
	            //Compile jrxml file
	            JasperDesign design = JRXmlLoader.load(fis);
	            JasperReport report = JasperCompileManager.compileReport(design);
	            
	            //Create jasper print object
	            Map<String,String> map = new HashMap<>();
	            
	            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, con);
	            
	            //Export the report
	            OutputStream os = response.getOutputStream();
	            JasperExportManager.exportReportToPdfStream(jasperPrint, os);
	            
	        }catch(Exception e){
	            System.out.println(e);
	        }
		}
		//Sell products xlsx
		@RequestMapping(path = "/admin/reportes/totalVentasXlsx", method = RequestMethod.GET)
		public @ResponseBody void totalVentasXlsx(HttpServletResponse response){
			try{
				
				Connection con = dataSource.getConnection();
	            //Load jrxml file
	            InputStream fis = new FileInputStream(new File("src/main/resources/reports/TotalVentas.jrxml"));
	            
	            //Compile jrxml file
	            JasperDesign design = JRXmlLoader.load(fis);
	            JasperReport report = JasperCompileManager.compileReport(design);
	            
	            //Create jasper print object
	            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, con);
	            
	            //Export the report
	            OutputStream os = response.getOutputStream();
	            JRXlsxExporter exporter = new JRXlsxExporter();
	            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("TotalVentas.xlsx"));
	            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
	            
	            exporter.exportReport();
	            
	        }catch(Exception e){
	            System.out.println(e);
	        }
		}
	
	//Factura pdf
	@RequestMapping(path = "/pago/factura", method = RequestMethod.POST)
	public @ResponseBody void reportFactura(HttpServletResponse response, @RequestParam Map<String, String> requestParams){
		try{
			Connection con = dataSource.getConnection();
            //Load jrxml file
            InputStream fis = new FileInputStream(new File("src/main/resources/reports/factura.jrxml"));
            
            //Compile jrxml file
            JasperDesign design = JRXmlLoader.load(fis);
            JasperReport report = JasperCompileManager.compileReport(design);
            
            //Create jasper print object
            Map<String,Object> map = new HashMap<>();
            map.put("idOrden", requestParams.get("idOrden"));
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, con);
            
            //Export the report
            OutputStream os = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, os);
            
        }catch(Exception e){
            System.out.println(e);
        }
	}
		
		
}
