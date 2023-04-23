package tds.PhotoTDS;

import java.util.*;

public class Notificacion {

	//Atributos
	private int id;
	private Date fecha;
	private int publicacion;
	private String mensaje;
	
	//Constructor
	public Notificacion(Date fecha, int publicacion, String mensaje) {
		this.fecha = fecha;
		this.publicacion = publicacion;
		this.mensaje = mensaje;
	}


	//Metodos
	public Date getFecha() {
		return fecha;
	}

	public int getPublicacion() {
		return publicacion;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
