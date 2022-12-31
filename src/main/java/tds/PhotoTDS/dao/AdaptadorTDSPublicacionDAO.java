package tds.PhotoTDS.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.PhotoTDS.Comentario;
import tds.PhotoTDS.Publicacion;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;


public class AdaptadorTDSPublicacionDAO implements IAdaptadorPublicacionDAO {

	ServicioPersistencia servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private PoolDAO poolPublicaciones = PoolDAO.getInstance();
	private static AdaptadorTDSPublicacionDAO unicaInstancia;
	
	@Override
	public void registrarPublicacion(Publicacion publicacion) {
		boolean existe = false;
		Entidad ePublicacion = null;
		try {
			ePublicacion = servPersistencia.recuperarEntidad(publicacion.getId());
		} catch (NullPointerException e) {
			existe = true;
		}
		if (existe || ePublicacion != null) return;
		
		ePublicacion = new Entidad();
		ePublicacion.setNombre("publicacion");
		ePublicacion.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("titulo", publicacion.getTitulo()),
						new Propiedad("fecha", dateFormat.format(publicacion.getFecha())),
						new Propiedad("descripcion", publicacion.getDescripcion()),
						new Propiedad("meGusta", String.valueOf(publicacion.getMeGusta())),
						new Propiedad("hashtags", AuxiliarDAO.obtenerCadenaDeIds(publicacion.getHashtags())),
						new Propiedad("usuario", publicacion.getUsuario()),
						new Propiedad("comentarios", AuxiliarDAO.obtenerIdsComentarios(publicacion.getComentarios()))
		))
		);
		ePublicacion = servPersistencia.registrarEntidad(ePublicacion);
		publicacion.setId(ePublicacion.getId());
	}

	@Override
	public void borrarPublicacion(Publicacion publicacion) {
		Entidad ePublicacion;
		ePublicacion = servPersistencia.recuperarEntidad(publicacion.getId());
		
		servPersistencia.borrarEntidad(ePublicacion);
		
	}

	@Override
	public void modificarPublicacion(Publicacion publicacion) {
		Entidad ePublicacion = servPersistencia.recuperarEntidad(publicacion.getId());
		
		for (Propiedad propiedad : ePublicacion.getPropiedades()) {
			if(propiedad.getNombre().equals("titulo")) {
				propiedad.setValor(publicacion.getTitulo());
			}
			if(propiedad.getNombre().equals("fecha")) {
				propiedad.setValor(publicacion.getFecha().toString());
			}
			if(propiedad.getNombre().equals("descripcion")) {
				propiedad.setValor(publicacion.getDescripcion());
			}
			if(propiedad.getNombre().equals("meGusta")) {
				propiedad.setValor(String.valueOf(publicacion.getMeGusta()));
			}
			if(propiedad.getNombre().equals("hashtags")) {
				propiedad.setValor(AuxiliarDAO.obtenerCadenaDeIds(publicacion.getHashtags()));
			}
			if(propiedad.getNombre().equals("usuario")) {
				propiedad.setValor(String.valueOf(publicacion.getUsuario()));
			}
			if(propiedad.getNombre().equals("comentarios")) {
				propiedad.setValor(AuxiliarDAO.obtenerIdsComentarios(publicacion.getComentarios()));
			}
		}
	}

	@Override
	public Publicacion recuperarPublicacion(int codigo) throws Exception {
		if (poolPublicaciones.contains(codigo)) return (Publicacion) poolPublicaciones.getObject(codigo);
		
		String titulo = null;
		Date fecha = null;
		String descripcion = null;
		int meGusta = 0;
		ArrayList<String> hashtags = null;
		ArrayList<Comentario> comentarios = null;
		String usuario = null;
		
		Entidad ePublicacion = servPersistencia.recuperarEntidad(codigo);
		titulo = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "titulo");
		descripcion = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "descripcion");
		meGusta = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(ePublicacion, "meGusta"));
		usuario = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "usuario");
		try {
			fecha = dateFormat.parse(servPersistencia.recuperarPropiedadEntidad(ePublicacion, "fecha"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		hashtags = (ArrayList<String>) AuxiliarDAO.obtenerListaDeIds(servPersistencia.recuperarPropiedadEntidad(ePublicacion, "hashtags"));
		comentarios = (ArrayList<Comentario>) AuxiliarDAO.obtenerComentariosDesdeIds(servPersistencia.recuperarPropiedadEntidad(ePublicacion, "comentarios"));
		
		Publicacion publicacion = new Publicacion(titulo, descripcion, hashtags, usuario);
		publicacion.setComentarios(comentarios);
		publicacion.setFecha(fecha);
		publicacion.setMeGusta(meGusta);
		
		return publicacion;
	}

	@Override
	public List<Publicacion> recuperarTodasPublicaciones() throws Exception {
		List<Publicacion> publicaciones = new ArrayList<Publicacion>();
        List<Entidad> ePublicaciones = servPersistencia.recuperarEntidades("publicacion");
        for(Entidad e : ePublicaciones) {
        	publicaciones.add(recuperarPublicacion(e.getId()));
        }
        return publicaciones;
	}

	public static AdaptadorTDSPublicacionDAO getUnicaInstancia() {
		if (unicaInstancia == null)
			 return new AdaptadorTDSPublicacionDAO();
		else
			 return unicaInstancia; 
	}

	
}
