package tds.PhotoTDS;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import tds.PhotoTDS.CargadorFotos.FotosEvent;
import tds.PhotoTDS.CargadorFotos.FotosListener;
import tds.PhotoTDS.dao.FactoriaDAO;
import tds.PhotoTDS.dao.IAdaptadorComentarioDAO;
import tds.PhotoTDS.dao.IAdaptadorFotoDAO;
import tds.PhotoTDS.dao.IAdaptadorNotificacionDAO;
import tds.PhotoTDS.dao.IAdaptadorTDSAlbumDAO;
import tds.PhotoTDS.dao.IAdaptadorUsuarioDAO;
import tds.driver.ServicioPersistencia;

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
	
	//Ruta de imágenes
	public static String pathFotos = "/src/main/java/images/";
	
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
		for(Foto f : album.getFotos()) {
			adaptadorFoto.registrarFoto(f);
		}
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
	
	public void addMeGusta(Publicacion publicacion) {
		publicacion.addMeGusta();
		if (publicacion instanceof Foto) {
			adaptadorFoto.modificarFoto((Foto) publicacion);
		} else {
			adaptadorAlbum.modificarAlbum((Album) publicacion);
		}
	}
	
	public Foto getFoto(int id) throws Exception {
		return repPublicaciones.getFoto(id);
	}

	@Override
	public void cargarFotos(FotosEvent e) {
		//TODO Java Beans
	}
	
	public Usuario getUsuario(int id) throws Exception {
		return repUsuarios.getUsuario(id);
	}
	
	public Usuario iniciarSesion(String nombreUsuario, String passUsuario) {
        List<Usuario> list = new ArrayList<Usuario>();
		try {
			list = unicaInstancia.adaptadorUsuario.recuperarTodosUsuarios(); //TODO CAMBIAR A ACCEDER REPOSITORIO EN VEZ DE ADAPTADOR
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(Usuario usuario : list) {
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
	
	//Función para la búsqueda de usuarios
	public ArrayList<Usuario> getUsuariosBusqueda(String nombre) throws Exception{
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) repUsuarios.getUsuarios();
		ArrayList<Usuario> usuariosMatch = (ArrayList<Usuario>) usuarios.stream()
											.filter(u -> u.getNombre().contains(nombre) || u.getEmail().contains(nombre))
											.collect(Collectors.toList());
		return usuariosMatch;
	}
	
	//Función para la búsqueda de hashtags (Sólo tiene en cuenta los MeGusta de las fotos, no de los álbumes)
	public Map<String, Integer> getHashtagsBusqueda(String hashtag) throws Exception {
		ArrayList<Foto> fotos = (ArrayList<Foto>) repPublicaciones.getFotos();
		ArrayList<String> hashtags = (ArrayList<String>) fotos.stream()
										.flatMap(f -> f.getHashtags().stream())
										.filter(h -> h.contains(hashtag))
										.collect(Collectors.toList());
		int seguidores = 0;
		Map<String, Integer> resultado = new HashMap<String, Integer>();
		for (String h : hashtags) {
			seguidores = fotos.stream()
				.filter(f -> f.getHashtags().contains(h))
				.map(f -> f.getUsuario())
				.distinct()
				.collect(Collectors.summingInt(u -> {
					int r = 0;
					try {
						r = repUsuarios.getUsuario(u).getUsuariosSeguidos().size();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return r;
				}));
			resultado.put(h, seguidores);
			seguidores = 0;
		}
		return resultado;
	}
	
	//Método que devuelve las fotos de un usuario
	public ArrayList<Foto> getFotosUsuario(int id) throws Exception {
		Usuario usuario = repUsuarios.getUsuario(id);
		return (ArrayList<Foto>) repPublicaciones.getFotos().stream()
				.filter(f -> f.getUsuario() == usuario.getId())
				.collect(Collectors.toList());
	}
	
	//Método que devuelve las fotos de los seguidos por un usuario
	public ArrayList<Foto> getFotosSeguidos(int id) throws Exception{
		Usuario usuario = repUsuarios.getUsuario(id);
		ArrayList<Foto> fotos = new ArrayList<Foto> (usuario.getUsuariosSeguidos().stream()
								.flatMap(uId -> {
									try {
										return getFotosUsuario(uId).stream();
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									return null;
								})
								.collect(Collectors.toList()));
		fotos.addAll(getFotosUsuario(id));
		return (ArrayList<Foto>) fotos.stream()
				.sorted(Comparator.comparing(Foto::getFecha))
				.collect(Collectors.toList());
	}
	
}
