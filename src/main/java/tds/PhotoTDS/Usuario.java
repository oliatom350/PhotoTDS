package tds.PhotoTDS;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
	
	//Atributos
	private int id;
	private String nombre;
	private String email;
	private String nombreCompleto;
	private Date fechaNacimiento;
	private boolean isPremium;
	private ArrayList<Integer> usuariosSeguidores;
	private ArrayList<Integer> usuariosSeguidos;
	private ArrayList<Notificacion> notificaciones;
	private String password;
	private String fotoPerfil; //QUE GUARDE SOLO EL NOMBRE DE LA FOTO
	private String presentacion;
	private ICalcularDescuento descuento;
	
	public Usuario(String nombre, String email, String nombreCompleto, Date fechaNacimiento, boolean isPremium, String password, String fotoPerfil, String presentacion) {
		this.nombre = nombre;
		this.email = email;
		this.nombreCompleto = nombreCompleto;
		this.fechaNacimiento = fechaNacimiento;
		this.isPremium = isPremium;
		this.usuariosSeguidores = new ArrayList<Integer>();
		this.notificaciones = new ArrayList<Notificacion>();
		this.usuariosSeguidos = new ArrayList<Integer>();
		this.password = password;
		this.fotoPerfil = fotoPerfil;
		this.presentacion = presentacion;
	}

	//Metodos
	

	public String getFotoPerfil() {
		return fotoPerfil;
	}
	
	public String getPathFotoPerfil() {
		return System.getProperty("user.dir") + PhotoTDS.pathFotos + fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

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
	
	public ArrayList<Integer> getUsuariosSeguidores() {
		return usuariosSeguidores;
	}

	public ArrayList<Integer> getUsuariosSeguidos(){
		return usuariosSeguidos;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUsuariosSeguidores(ArrayList<Integer> usuariosSeguidores) {
		this.usuariosSeguidores = usuariosSeguidores;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addSeguidor(int seguidor) {
		this.usuariosSeguidores.add(seguidor);
	}
	
	public void addSeguido(int seguido) {
		this.usuariosSeguidos.add(seguido);
	}
	
	public void addNotificacion(Notificacion notificacion) {
		this.notificaciones.add(notificacion);
	}
	
	//Métodos del patrón Estrategia para calcular el precio de hacerse premium
	public void setDescuento(ICalcularDescuento descuento) {
		this.descuento = descuento;
	}
	
	public double calcularPrecio() {
		return descuento.getPrecioPremium(this);
	}
	
	
	
}
