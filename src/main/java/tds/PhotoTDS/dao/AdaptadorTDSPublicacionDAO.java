package tds.PhotoTDS.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
						new Propiedad("hashtags", publicacion.getHashtags().toString()),
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
				propiedad.setValor(String.valueOf(publicacion.getHashtags().toString()));
			}
			if(propiedad.getNombre().equals("usuario")) {
				propiedad.setValor(String.valueOf(publicacion.getUsuario()));
			}
			if(propiedad.getNombre().equals("comentarios")) {
				propiedad.setValor(String.valueOf(AuxiliarDAO.obtenerIdsComentarios(publicacion.getComentarios())));
			}
		}
	}

	@Override
	public Publicacion recuperarPublicacion(int codigo) {
		if (poolPublicaciones.contains(codigo)) return (Publicacion) poolPublicaciones.getObject(codigo);
		
		String titulo = null;
		Date fecha = null;
		String descripcion = null;
		int meGusta = 0;
		ArrayList<String> hashtags = null;
		ArrayList<Comentario> comentarios = null;
		String usuario = null;
		
		
		
		return null;
	}

	@Override
	public List<Publicacion> recuperarTodasPublicaciones() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
