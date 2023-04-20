package tds.PhotoTDS.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.PhotoTDS.Comentario;
import tds.PhotoTDS.Foto;
import tds.PhotoTDS.Album;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorTDSAlbumDAO implements IAdaptadorTDSAlbumDAO{
	
	ServicioPersistencia servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private PoolDAO poolAlbums = PoolDAO.getInstance();
	private static AdaptadorTDSAlbumDAO unicaInstancia;
	
	public void registrarAlbum(Album album) {
		boolean existe = false;
		Entidad eAlbum = null;
		try {
			eAlbum = servPersistencia.recuperarEntidad(album.getId());
		} catch (NullPointerException e) {
			existe = true;
		}
		if (existe || eAlbum != null) return;
		
		eAlbum = new Entidad();
		eAlbum.setNombre("album");
		eAlbum.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("titulo", album.getTitulo()),
						new Propiedad("fecha", dateFormat.format(album.getFecha())),
						new Propiedad("descripcion", album.getDescripcion()),
						new Propiedad("meGusta", String.valueOf(album.getMeGusta())),
						new Propiedad("hashtags", AuxiliarDAO.obtenerCadenaDeIds(album.getHashtags())),
						new Propiedad("usuario", String.valueOf(album.getUsuario())),
						new Propiedad("comentarios", AuxiliarDAO.obtenerIdsComentarios(album.getComentarios())),
						new Propiedad("fotos", AuxiliarDAO.obtenerCadenaDeIds(album.getFotos().stream()
																				.map(f -> String.valueOf(f.getId()))
																				.collect(Collectors.toList())))))
		);
		eAlbum = servPersistencia.registrarEntidad(eAlbum);
		album.setId(eAlbum.getId());
	}
	
	@Override
	public void borrarAlbum(Album album) {
		Entidad eAlbum;
		eAlbum = servPersistencia.recuperarEntidad(album.getId());
		
		servPersistencia.borrarEntidad(eAlbum);
		
	}

	@Override
	public void modificarAlbum(Album album) {
		Entidad eAlbum = servPersistencia.recuperarEntidad(album.getId());
		
		for (Propiedad propiedad : eAlbum.getPropiedades()) {
			if(propiedad.getNombre().equals("titulo")) {
				propiedad.setValor(album.getTitulo());
			}
			if(propiedad.getNombre().equals("fecha")) {
				propiedad.setValor(album.getFecha().toString());
			}
			if(propiedad.getNombre().equals("descripcion")) {
				propiedad.setValor(album.getDescripcion());
			}
			if(propiedad.getNombre().equals("meGusta")) {
				propiedad.setValor(String.valueOf(album.getMeGusta()));
			}
			if(propiedad.getNombre().equals("hashtags")) {
				propiedad.setValor(AuxiliarDAO.obtenerCadenaDeIds(album.getHashtags()));
			}
			if(propiedad.getNombre().equals("usuario")) {
				propiedad.setValor(String.valueOf(album.getUsuario()));
			}
			if(propiedad.getNombre().equals("comentarios")) {
				propiedad.setValor(AuxiliarDAO.obtenerIdsComentarios(album.getComentarios()));
			}
			if(propiedad.getNombre().equals("fotos")) {
				propiedad.setValor(AuxiliarDAO.obtenerCadenaDeIds(album.getFotos().stream()
									.map(f -> String.valueOf(f.getId()))
									.collect(Collectors.toList())));
			}
		}
	}

	@Override
	public Album recuperarAlbum(int codigo) throws Exception {
		if (poolAlbums.contains(codigo)) return (Album) poolAlbums.getObject(codigo);
		
		String titulo = null;
		Date fecha = null;
		String descripcion = null;
		int meGusta = 0;
		ArrayList<String> hashtags = null;
		ArrayList<Comentario> comentarios = null;
		int usuario = 0;
		List<String> idfotos = null;
		
		Entidad eAlbum = servPersistencia.recuperarEntidad(codigo);
		titulo = servPersistencia.recuperarPropiedadEntidad(eAlbum, "titulo");
		descripcion = servPersistencia.recuperarPropiedadEntidad(eAlbum, "descripcion");
		meGusta = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eAlbum, "meGusta"));
		usuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eAlbum, "usuario"));
		idfotos = AuxiliarDAO.obtenerListaDeIds(servPersistencia.recuperarPropiedadEntidad(eAlbum, "fotos"));
		try {
			fecha = dateFormat.parse(servPersistencia.recuperarPropiedadEntidad(eAlbum, "fecha"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		hashtags = (ArrayList<String>) AuxiliarDAO.obtenerListaDeIds(servPersistencia.recuperarPropiedadEntidad(eAlbum, "hashtags"));
		comentarios = (ArrayList<Comentario>) AuxiliarDAO.obtenerComentariosDesdeIds(servPersistencia.recuperarPropiedadEntidad(eAlbum, "comentarios"));
		LinkedList<Foto> fotos = new LinkedList<Foto>();
		IAdaptadorFotoDAO adaptadorFoto = AdaptadorTDSFotoDAO.getUnicaInstancia();
		for(String id : idfotos) {
			fotos.add(adaptadorFoto.recuperarFoto(Integer.parseInt(id)));
		}
		
		Album album = new Album(titulo, descripcion, hashtags, usuario, fotos);
		album.setComentarios(comentarios);
		album.setFecha(fecha);
		album.setMeGusta(meGusta);
		album.setId(codigo);
		
		return album;
	}

	@Override
	public List<Album> recuperarTodasAlbums() throws Exception {
		List<Album> albumes = new ArrayList<Album>();
        List<Entidad> eAlbumes = servPersistencia.recuperarEntidades("album");
        for(Entidad e : eAlbumes) {
        	albumes.add(recuperarAlbum(e.getId()));
        }
        return albumes;
	}

	public static AdaptadorTDSAlbumDAO getUnicaInstancia() {
		if (unicaInstancia == null)
			 return new AdaptadorTDSAlbumDAO();
		else
			 return unicaInstancia; 
	}
}
