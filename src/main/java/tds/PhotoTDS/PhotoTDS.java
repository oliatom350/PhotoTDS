package tds.PhotoTDS;

import tds.PhotoTDS.dao.FactoriaDAO;
import tds.PhotoTDS.dao.IAdaptadorComentarioDAO;
import tds.PhotoTDS.dao.IAdaptadorNotificacionDAO;
import tds.PhotoTDS.dao.IAdaptadorPublicacionDAO;
import tds.PhotoTDS.dao.IAdaptadorUsuarioDAO;

public class PhotoTDS {

	//Atributos
	private static PhotoTDS unicaInstancia = new PhotoTDS();
	private RepoUsuarios repUsuarios;
	private RepoPublicaciones repPublicaciones;
	private GeneradorPDF genPDF;
	private GeneradorExcel genExcel;
	private IAdaptadorNotificacionDAO adaptadorNotificacion;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorComentarioDAO adaptadorComentario;
	private IAdaptadorPublicacionDAO adaptadorPublicacion;
	
	//Constructor
	public PhotoTDS() {
		inicializarAdaptadores();
		inicializarRepos();
	}
	
	public void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getFactoriaDAO(FactoriaDAO.DAO_TDS);
		} catch(Exception e) {
			e.printStackTrace();
		}
		adaptadorComentario = factoria.getComentarioDAO();
		adaptadorNotificacion = factoria.getNotificacionDAO();
		adaptadorPublicacion = factoria.getPublicacionDAO();
		adaptadorUsuario = factoria.getUsuarioDAO();
	}
	
	public void inicializarRepos() {
		repUsuarios.setUsuarios(adaptadorUsuario.recuperarTodosUsuarios());
		repPublicaciones.setPublicaciones(adaptadorPublicacion.recuperarTodasPublicaciones());
	}
	
	//Metodos
	public RepoUsuarios getRepUsers() {
		return repUsuarios;
	}

	public RepoPublicaciones getRepPubli() {
		return repPublicaciones;
	}

	public GeneradorPDF getGenPDF() {
		return genPDF;
	}

	public GeneradorExcel getGenExcel() {
		return genExcel;
	}

	public void registrarUsuario(Usuario usuario) {
		for(Notificacion n : usuario.getNotificaciones()) {
			adaptadorNotificacion.registrarNotificacion(n);
		}
		adaptadorUsuario.registrarUsuario(usuario);
		repUsuarios.addUsuario(usuario);
	}
	
	public void registrarPublicacion(Publicacion publicacion) {
		adaptadorPublicacion.registrarPublicacion(publicacion);
		repPublicaciones.addPublicacion(publicacion);
	}
	
	public void addComentario(Comentario comentario, Publicacion publicacion) {
		publicacion.addComentario(comentario);
		adaptadorPublicacion.modificarPublicacion(publicacion);
		adaptadorComentario.registrarComentario(comentario);
	}
	
	public void addFoto() {
		
	}
}
