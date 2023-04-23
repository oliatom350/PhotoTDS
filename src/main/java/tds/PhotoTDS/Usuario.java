package tds.PhotoTDS;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

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
		if (!this.usuariosSeguidores.contains(seguidor)) {
			this.usuariosSeguidores.add(seguidor);
		}
	}
	
	public void removeSeguidor(int seguidor) {
		if (this.usuariosSeguidores.contains(seguidor)) {
			usuariosSeguidores.remove(usuariosSeguidores.indexOf(seguidor));
		}
	}
	
	public void addSeguido(int seguido) {
		if (!this.usuariosSeguidos.contains(seguido)) {
			this.usuariosSeguidos.add(seguido);
		}
	}
	
	public void removeSeguido(int seguido) {
		if (this.usuariosSeguidos.contains(seguido)) {
			usuariosSeguidos.remove(usuariosSeguidos.indexOf(seguido));
		}
	}
	
	public void addNotificacion(Notificacion notificacion) {
		this.notificaciones.add(notificacion);
	}
	
	//Patron Estrategia
	public HashSet<Descuento> getDescuentos() {
		//Set de descuentos a devolver
		HashSet<Descuento> descuentos = new HashSet<Descuento>();
		//DescuentoEdad
		//Condiciones:
		int edad = calculaEdad();
		//1-Entre 18 y 25 años, un 25%
		if (edad <= 25 && edad >= 18) {
			descuentos.add(new DescuentoEdad(true)); 
		}
		//2-Para mayores de 65 años, un 15%
		else if (edad > 65) {
			descuentos.add(new DescuentoEdad(false)); 
		}
		
		//DescuentoLikes
		//Condiciones:
		//1-Si tiene más de 100 "me gusta", un 20%
		int numMG = calculaMG();
		if (numMG > 100) {
			descuentos.add(new DescuentoPopularidad());
		}	
		return descuentos;
	}
	
	public int calculaEdad() {
		LocalDate now = LocalDate.now();
		LocalDate birthday = this.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return Period.between(birthday, now).getYears();
	}
	
	public int calculaMG() {
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		ArrayList<Foto> publicaciones = new ArrayList<Foto>();
		try {
			publicaciones = controlador.getFotosUsuario(this.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publicaciones.stream()
					.mapToInt(p -> p.getMeGusta())
					.sum();
	}
	
}
