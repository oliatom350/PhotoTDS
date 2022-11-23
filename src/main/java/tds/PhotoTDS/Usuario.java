package tds.PhotoTDS;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
	
	//Atributos
	
	private String nombre;
	private String email;
	private String nombreCompleto;
	private Date fechaNacimiento;
	private boolean isPremium;
	private ArrayList<String> usuariosSeguidores;
	private ArrayList<Notificacion> notificaciones;
	
	public Usuario(String nombre, String email, String nombreCompleto, Date fechaNacimiento, boolean isPremium) {
		this.nombre = nombre;
		this.email = email;
		this.nombreCompleto = nombreCompleto;
		this.fechaNacimiento = fechaNacimiento;
		this.isPremium = isPremium;
		this.usuariosSeguidores = new ArrayList<String>();
		this.notificaciones = new ArrayList<Notificacion>();
	}

	//Metodos
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isPremium() {
		return isPremium;
	}

	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}

	public String getEmail() {
		return email;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public ArrayList<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void addSeguidor(String seguidor) {
		this.usuariosSeguidores.add(seguidor);
	}
	
	public void addNotificacion(Notificacion notificacion) {
		this.notificaciones.add(notificacion);
	}
	
}
