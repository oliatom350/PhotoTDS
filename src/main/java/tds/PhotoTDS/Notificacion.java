package tds.PhotoTDS;

import java.util.*;

public class Notificacion {

	//Atributos
	private int id;
	private Date fecha;
	private int publicacion;
	
	//Constructor
	public Notificacion(Date fecha, int publicacion) {
		this.fecha = fecha;
		this.publicacion = publicacion;
	}


	//Metodos
	public Date getFecha() {
		return fecha;
	}

	public int getPublicacion() {
		return publicacion;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
