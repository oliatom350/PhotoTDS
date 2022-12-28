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
			factoria = FactoriaDAO.getFactoriaDAO();
		} catch(Exception e) {
			e.printStackTrace();
		}
		adaptadorComentario = factoria.getComentarioDAO();
		adaptadorNotificacion = factoria.getNotificacionDAO();
		adaptadorPublicacion = factoria.getPublicacionDAO();
		adaptadorUsuario = factoria.getUsuarioDAO();
	}
	
	public void inicializarRepos() {
		
	}
	
	//Metodos
	public RepoUsuarios getRepUsers() {
		return repUsers;
	}

	public RepoPublicaciones getRepPubli() {
		return repPubli;
	}

	public GeneradorPDF getGenPDF() {
		return genPDF;
	}

	public GeneradorExcel getGenExcel() {
		return genExcel;
	}

	public void registrarUsuario() {
		
	}
	
	public void addFoto() {
		
	}
}
