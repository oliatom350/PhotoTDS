package tds.PhotoTDS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public static ArrayList<Foto> getFotosOrdenadas(List<Foto> fotos){
		return (ArrayList<Foto>) fotos.stream()
				.sorted(Comparator.comparing(Foto::getFecha))
				.collect(Collectors.toList());
	}
	
}
