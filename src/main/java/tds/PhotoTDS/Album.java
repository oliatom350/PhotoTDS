package tds.PhotoTDS;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album extends Publicacion {

	//Atributos
	private LinkedList<Foto> fotos;

	//Constructor
	public Album(String titulo, String descripcion, ArrayList<String> hashtags, int usuario, LinkedList<Foto> fotos) {
		super(titulo, descripcion, hashtags, usuario);
		this.fotos = fotos;
	}
	
	//Metodos
	public LinkedList<Foto> getFotos() {
		return fotos;
	}

	public void addFoto(Foto foto) {
		this.fotos.add(foto);
	}
	
	public void removeFoto(Foto foto) {
		this.fotos.remove(foto);
	}
	
	public void addMeGusta() {
		super.addMeGusta();
		fotos.stream().forEach(f -> f.addMeGusta());
	}
	
}
