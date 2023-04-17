package tds.PhotoTDS;

import java.util.ArrayList;

public class Foto extends Publicacion {

	//Atributos
	private String path;
	
	//Constructor
	public Foto(String titulo, String descripcion, ArrayList<String> hashtags, int usuario, String ruta) {
		super(titulo, descripcion, hashtags, usuario);
		this.path = ruta;
	}

	//Metodos
	public String getPath() {
		return path;
	}
	
}
