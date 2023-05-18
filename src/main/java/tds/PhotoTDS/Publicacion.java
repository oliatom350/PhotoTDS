package tds.PhotoTDS;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Publicacion {

	//Atributos
	private int id;
	private String titulo;
	private Date fecha;
	private String descripcion;
	private int meGusta;
	private ArrayList<String> hashtags;
	private ArrayList<Comentario> comentarios;
	private int usuario;
	
	//Constructor
	public Publicacion(String titulo, String descripcion, ArrayList<String> hashtags, int usuario) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.hashtags = hashtags;
		this.fecha = new Date();
		this.meGusta = 0;
		this.comentarios = new ArrayList<Comentario>();
		this.usuario = usuario;
	}
	
	//Metodos

	public String getTitulo() {
		return titulo;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getMeGusta() {
		return meGusta;
	}

	public ArrayList<String> getHashtags() {
		return hashtags;
	}

	public ArrayList<Comentario> getComentarios() {
		return comentarios;
	}

	public int getUsuario() {
		return usuario;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setMeGusta(int meGusta) {
		this.meGusta = meGusta;
	}

	public void setComentarios(ArrayList<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void addMeGusta() {
		this.meGusta++;
	}
	
	public void addComentario (Comentario comentario) {
		this.comentarios.add(comentario);
	}
	
	public static ArrayList<String> getHashtags(String descripcion){
		Pattern pt = Pattern.compile("#(\\S+)");
		Matcher m = pt.matcher(descripcion);
		ArrayList<String> list = new ArrayList<String>();
		while(m.find()) {
			list.add(m.group(1));
		}
		
		// Se comprueba que los hashtags no pasen el límite
		if (list.stream()
				.anyMatch(h -> h.length() > 15) || list.size() > 4)
			return null;
		return list;
	}
	
}
