package tds.PhotoTDS;

import java.util.ArrayList;

public class Foto extends Publicacion {

	//Atributos
	private String nombre;
	
	//Constructor
	public Foto(String titulo, String descripcion, ArrayList<String> hashtags, int usuario, String nombreFoto) {
		super(titulo, descripcion, hashtags, usuario);
		this.nombre = nombreFoto;
	}

	//Metodos
	public String getNombre() {
		return nombre;
	}
	
	public String getPath() {
		return System.getProperty("user.dir") + PhotoTDS.pathFotos + nombre;
	}
	
	public String getFotoPerfil(int idUsuario) {
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		return controlador.getRepUsers().getUsuario(idUsuario).getFotoPerfil();
	}
	
	public String getNickUsuario(int idUsuario) {
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		return controlador.getRepUsers().getUsuario(idUsuario).getNombre();
	}
	
}
