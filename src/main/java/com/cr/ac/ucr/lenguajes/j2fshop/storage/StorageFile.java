package com.cr.ac.ucr.lenguajes.j2fshop.storage;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cr.ac.ucr.lenguajes.j2fshop.business.ProductoService;

public class StorageFile {

	private String url = "src/main/resources/static/images";		
	FileSystem sistemaFicheros= FileSystems.getDefault();
	
	@Autowired
	ProductoService prductoService;
	
	private final Path rootLocation = Paths.get(url);
	
	public StorageFile() {
		
	}

	public String getStringPath() {
		return url;
	}

	public String formato(String nomOriginal){
		String pnt = nomOriginal.substring(nomOriginal.length()-5 ,nomOriginal.length()-4);	
		if(pnt.equals(".")){
			return nomOriginal.substring(nomOriginal.length()-5, nomOriginal.length());
		}else{
			return nomOriginal.substring(nomOriginal.length()-4, nomOriginal.length());
		}
	}

	public String getUriImage(int codProducto, String formato){
		return "/images/PD"+codProducto+formato;
	}

	public String guardarImagen(CommonsMultipartFile file, String name) {
		String formato = formato(file.getFileItem().getName());
		name+=formato;
		
		try {
			Files.copy(file.getInputStream(), this.rootLocation.resolve(name),
					StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}

}
