package tds.PhotoTDS;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import tds.PhotoTDS.CargadorFotos.FotosEvent;
import tds.PhotoTDS.CargadorFotos.FotosListener;
import tds.PhotoTDS.dao.FactoriaDAO;
import tds.PhotoTDS.dao.IAdaptadorComentarioDAO;
import tds.PhotoTDS.dao.IAdaptadorFotoDAO;
import tds.PhotoTDS.dao.IAdaptadorNotificacionDAO;
import tds.PhotoTDS.dao.IAdaptadorTDSAlbumDAO;
import tds.PhotoTDS.dao.IAdaptadorUsuarioDAO;

public class PhotoTDS implements FotosListener {

	//Atributos
	private static PhotoTDS unicaInstancia = new PhotoTDS();
	private RepoUsuarios repUsuarios;
	private RepoPublicaciones repPublicaciones;
	private GeneradorPDF genPDF;
	private GeneradorExcel genExcel;
	private IAdaptadorNotificacionDAO adaptadorNotificacion;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorComentarioDAO adaptadorComentario;
	private IAdaptadorFotoDAO adaptadorFoto;
	private IAdaptadorTDSAlbumDAO adaptadorAlbum;
	
	//Constructor
	public PhotoTDS() {
		inicializarAdaptadores();
		inicializarRepos();
	}
	
	public static PhotoTDS getUnicaInstancia() {
		 if (unicaInstancia == null)
		 unicaInstancia = new PhotoTDS();
		 return unicaInstancia;
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
		adaptadorAlbum = factoria.getAlbumDAO();
		adaptadorFoto = factoria.getFotoDAO();
		adaptadorUsuario = factoria.getUsuarioDAO();
	}
	
	public void inicializarRepos() {
		repUsuarios = RepoUsuarios.getInstancia();
		repPublicaciones = RepoPublicaciones.getInstancia();
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
	
	public void registrarFoto(Foto foto) {
		adaptadorFoto.registrarFoto(foto);
		repPublicaciones.addFoto(foto);
	}
	
	public void registrarAlbum(Album album) {
		adaptadorAlbum.registrarAlbum(album);
		repPublicaciones.addAlbum(album);
	}
	
	public void addComentario(Comentario comentario, Publicacion publicacion) {
		publicacion.addComentario(comentario);
		if (publicacion instanceof Foto) {
			adaptadorFoto.modificarFoto((Foto) publicacion);
		} else {
			adaptadorAlbum.modificarAlbum((Album) publicacion);
		}
		adaptadorComentario.registrarComentario(comentario);
	}
	
	public Foto getFoto(int id) throws Exception {
		return adaptadorFoto.recuperarFoto(id);
	}

	@Override
	public void cargarFotos(FotosEvent e) {
		//TODO
	}
	
	public Usuario getUsuario(int id) throws Exception {
		return adaptadorUsuario.recuperarUsuario(id);
	}
	
	public Usuario iniciarSesion(String nombreUsuario, String passUsuario) {
        for(Usuario usuario : unicaInstancia.getRepUsers().getUsuarios()) {
            if((usuario.getEmail().equals(nombreUsuario) || usuario.getNombre().equals(nombreUsuario)) && usuario.getPassword().equals(passUsuario))
                return usuario;
        }
        System.out.println("Nombre de usuario, email o contraseña incorrectos");
        return null;
    }
	
	public static Path copyFile(File currentFile) throws Exception {
		if (currentFile != null) {
			FileSystem fileSys = FileSystems.getDefault();
			Path srcPath = fileSys.getPath(currentFile.getAbsolutePath());
			Path destPath = fileSys.getPath("src/main/java/images/"+currentFile.getName());
			Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
			return destPath;
		}
		return null;
	}
}
