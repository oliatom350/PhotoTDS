package tds.PhotoTDS;

import java.util.*;

public class Notificacion {

	//Atributos
	private Date fecha;
	private Publicacion publicacion;
	
	//Constructor
	public Notificacion(Date fecha, Publicacion publicacion) {
		this.fecha = fecha;
		this.publicacion = publicacion;
	}

	
	//Metodos
	public Date getFecha() {
		return fecha;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}
}
