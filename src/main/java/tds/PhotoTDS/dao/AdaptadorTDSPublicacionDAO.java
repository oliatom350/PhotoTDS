package tds.PhotoTDS.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.PhotoTDS.Publicacion;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;


public class AdaptadorTDSPublicacionDAO implements IAdaptadorPublicacionDAO {

	ServicioPersistencia servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public void registrarPublicacion(Publicacion publicacion) {
		boolean existe = true;
		Entidad ePublicacion = null;
		try {
			ePublicacion = servPersistencia.recuperarEntidad(publicacion.getId());
		} catch (NullPointerException e) {
			existe = false;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Publicacion modificarPublicacion(Publicacion publicacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publicacion recuperarPublicacion(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publicacion> recuperarTodasPublicaciones() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
