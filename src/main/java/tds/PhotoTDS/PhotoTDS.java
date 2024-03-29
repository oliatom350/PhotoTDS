package tds.PhotoTDS;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import tds.PhotoTDS.CargadorFotos.CargadorFotos;
import tds.PhotoTDS.CargadorFotos.FotosEvent;
import tds.PhotoTDS.CargadorFotos.FotosListener;
import tds.PhotoTDS.dao.FactoriaDAO;
import tds.PhotoTDS.dao.IAdaptadorComentarioDAO;
import tds.PhotoTDS.dao.IAdaptadorFotoDAO;
import tds.PhotoTDS.dao.IAdaptadorNotificacionDAO;
import tds.PhotoTDS.dao.IAdaptadorTDSAlbumDAO;
import tds.PhotoTDS.dao.IAdaptadorUsuarioDAO;
import umu.tds.fotos.Fotos;

public class PhotoTDS implements FotosListener{

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
	private CargadorFotos cargador = new CargadorFotos();
	private Fotos fotosCargador;
	
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
		cargador.addFotosListener(this);
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

	public void registrarUsuario(String nombre, String email, String nombreCompleto, Date fechaNacimiento, boolean isPremium, String password, String fotoPerfil, String presentacion) {
		Usuario u = new Usuario(nombre, email, nombreCompleto, fechaNacimiento, isPremium, password, fotoPerfil, presentacion);
		for(Notificacion n : u.getNotificaciones()) {
			adaptadorNotificacion.registrarNotificacion(n);
		}
		adaptadorUsuario.registrarUsuario(u);
		repUsuarios.addUsuario(u);
	}
	
	public void cambiarPassword(int id, String newPass) {
		Usuario u;
		try {
			u = getUsuario(id);
			u.setPassword(newPass);
			adaptadorUsuario.modificarUsuario(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkOpcionales(int id, String descripcion, String fotoPerfil) {
		try {
			Usuario u = getUsuario(id);
			if (descripcion != "") 
				u.setPresentacion(descripcion);
			if (fotoPerfil != "")
				u.setFotoPerfil(fotoPerfil);
			modificarUsuario(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modificarUsuario(Usuario usuario) {
		for(Notificacion n : usuario.getNotificaciones()) {
			adaptadorNotificacion.registrarNotificacion(n);
		}
		adaptadorUsuario.modificarUsuario(usuario);
	}
	
	public void registrarNotificacion(Notificacion notificacion) {
		adaptadorNotificacion.registrarNotificacion(notificacion);
	}
	
	public void registrarFoto(Foto foto) {
		adaptadorFoto.registrarFoto(foto);
		repPublicaciones.addFoto(foto);
	}
	
	public Foto crearFoto(String titulo, String desc, ArrayList<String> hashtags, int usuario, String nombreFoto) {
		return new Foto(titulo, desc, hashtags, usuario, nombreFoto);
	}
	
	public void eliminarFoto(Foto foto) {
		adaptadorFoto.borrarFoto(foto);
		repPublicaciones.removeFoto(foto);
	}
	
	public void registrarAlbum(Album album) {
		adaptadorAlbum.registrarAlbum(album);
		for(Foto f : album.getFotos()) {
			adaptadorFoto.registrarFoto(f);
		}
		repPublicaciones.addAlbum(album);
	}
	
	public void modificarAlbum(Album album) {
		adaptadorAlbum.modificarAlbum(album);
	}
	
	public void eliminarAlbum(Album album) {
		adaptadorAlbum.borrarAlbum(album);
		repPublicaciones.removeAlbum(album);
	}
	
	public void addComentario(Comentario comentario, Publicacion publicacion) {
		adaptadorComentario.registrarComentario(comentario);
		publicacion.addComentario(comentario);
		if (publicacion instanceof Foto) {
			adaptadorFoto.modificarFoto((Foto) publicacion);
		} else {
			adaptadorAlbum.modificarAlbum((Album) publicacion);
		}
	}
	
	public void addMeGusta(Publicacion publicacion) {
		if (publicacion instanceof Foto) {
			publicacion.addMeGusta();
			adaptadorFoto.modificarFoto((Foto) publicacion);
		} else {
			Album album = (Album) publicacion;
			album.addMeGusta();
			adaptadorAlbum.modificarAlbum(album);
			for (Foto foto : album.getFotos()) {
            	adaptadorFoto.modificarFoto(foto);
            	for(Foto fotoR : repPublicaciones.getFotos()) {
            		if (fotoR.getId() == foto.getId()) {
            			fotoR.addMeGusta();
            		}
            	}
            }
		}
	}
	
	public Foto getFoto(int id) throws Exception {
		return repPublicaciones.getFoto(id);
	}


	
	public Usuario getUsuario(int id) throws Exception {
		return repUsuarios.getUsuario(id);
	}
	
	public String getFotoUsuario(int id) throws Exception {
		return getUsuario(id).getPathFotoPerfil();
	}
	
	public Usuario iniciarSesion(String nombreUsuario, String passUsuario) {
        List<Usuario> list = new ArrayList<Usuario>();
		try {
			//list = unicaInstancia.adaptadorUsuario.recuperarTodosUsuarios();
			list = repUsuarios.getUsuarios();
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
	
	public ArrayList<Foto> getFotosTop(){
		return (ArrayList<Foto>) repPublicaciones.getFotos().stream()
				.sorted((f1,f2) -> f2.getMeGusta() - f1.getMeGusta())
				.limit(10)
				.collect(Collectors.toList());
	}
	
	public ArrayList<Album> getAlbumesUsuario(int id) throws Exception {
		return (ArrayList<Album>) repPublicaciones.getAlbumes().stream()
				.filter(a -> a.getUsuario() == id)
				.collect(Collectors.toList());
	}
	
	public Album getAlbum(String nombre, int id) throws Exception {
		ArrayList<Album> albumes = this.getAlbumesUsuario(id);
		for (Album a : albumes) {
			if (a.getTitulo().equals(nombre)) return a;
		}
		return null;
	}
	
	//Método que devuelve las fotos de los seguidos por un usuario
	public ArrayList<Foto> getFotosSeguidos(int id) throws Exception{
		Usuario usuario = repUsuarios.getUsuario(id);
		ArrayList<Foto> fotos = new ArrayList<Foto> (usuario.getUsuariosSeguidos().stream()
								.flatMap(uId -> {
									try {
										return getFotosUsuario(uId).stream();
									} catch (Exception e) {
										e.printStackTrace();
									}
									return null;
								})
								.collect(Collectors.toList()));
		fotos.addAll(getFotosUsuario(id));
		return (ArrayList<Foto>) fotos.stream()
				.sorted(Comparator.comparing(Foto::getFecha).reversed())
				.collect(Collectors.toList());
	}
	
	public void addUsuarioNotificacion(Usuario usuario, Publicacion publicacion, String mensaje) {
		Date now = new Date();
		Notificacion n; 
		if (publicacion != null)
			n = new Notificacion(now, publicacion.getId(), mensaje);
		else
			n = new Notificacion(now, -1, mensaje);
		adaptadorNotificacion.registrarNotificacion(n);
		usuario.addNotificacion(n);
		modificarUsuario(usuario);
	}
	
	public void eliminarUsuarioNotificacion(Usuario usuario, Notificacion notificacion) {
		adaptadorNotificacion.borrarNotificacion(notificacion);
		usuario.removeNotificacion(notificacion);
		modificarUsuario(usuario);
	}
	
	public void addNotificacionSeguidores(int usuarioid, Publicacion publicacion) {
		Usuario usuario = repUsuarios.getUsuario(usuarioid);
		ArrayList<Usuario> seguidores = new ArrayList<Usuario>();
		for(int usuarioId : usuario.getUsuariosSeguidores())
			seguidores.add(repUsuarios.getUsuario(usuarioId));
		seguidores.forEach(s -> addUsuarioNotificacion(s, publicacion, "El usuario "+usuario.getNombre()+" ha publicado una foto o álbum!"));
	}

	
	public void cargarFotosPath(String pathFotos) {
		cargador.setArchivoFotos(pathFotos);
	}

	@Override
	public void cargarFotos(FotosEvent e) {
		fotosCargador = e.getFotos();
	}
	
	public Fotos getFotosCargador() {
		return fotosCargador;
	}
	
	public Album addFotoAlbum(Foto f, String album, int usuario) {
		try {
			Album a = getAlbum(album, usuario);
			a.addFoto(f);
			modificarAlbum(a);
			addNotificacionSeguidores(usuario, a);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void addSeguidor(Usuario seguido, Usuario seguidor) {
		seguido.addSeguidor(seguidor.getId());
		seguidor.addSeguido(seguido.getId());
		modificarUsuario(seguido);
		modificarUsuario(seguidor);
	}
	
	public void removeSeguidor(Usuario seguido, Usuario seguidor) {
		seguido.removeSeguidor(seguidor.getId());
		seguidor.removeSeguido(seguido.getId());
		modificarUsuario(seguido);
		modificarUsuario(seguidor);
	}
	
	public void removeFotoAlbum(Album a, Foto f) {
		a.removeFoto(f);
	}
	
}
