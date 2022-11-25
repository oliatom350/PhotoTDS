package tds.PhotoTDS.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.PhotoTDS.Notificacion;
import tds.PhotoTDS.Publicacion;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorTDSNotificacionDAO implements IAdaptadorNotificacionDAO{

	ServicioPersistencia servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public void registrarNotificacion(Notificacion notificacion) {
		boolean existe = false;
		Entidad eNotificacion = null;
		try {
			eNotificacion = servPersistencia.recuperarEntidad(notificacion.getId());
		} catch (NullPointerException e) {
			existe = true;
		}
		if (existe || eNotificacion != null) return;
		
		eNotificacion = new Entidad();
		eNotificacion.setNombre("notificacion");
		
		eNotificacion.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("fecha", notificacion.getFecha().toString()),
						new Propiedad("publicacion", Integer.toString(notificacion.getPublicacion().getId())) 
				))
		);
		eNotificacion = servPersistencia.registrarEntidad(eNotificacion);
		notificacion.setId(eNotificacion.getId());
	}

	@Override
	public void borrarNotificacion(Notificacion notificacion) {
		Entidad eNotificacion;
		eNotificacion = servPersistencia.recuperarEntidad(notificacion.getId());
		
		servPersistencia.borrarEntidad(eNotificacion);
	}

	@Override
	public void modificarUsuario(Notificacion notificacion) {
		Entidad eNotificacion = servPersistencia.recuperarEntidad(notificacion.getId());
		
		for (Propiedad propiedad : eNotificacion.getPropiedades()) {
			if(propiedad.getNombre().equals("fecha")) {
				propiedad.setValor(notificacion.getFecha().toString());
			}
			if(propiedad.getNombre().equals("publicacion")) {
				propiedad.setValor(Integer.toString(notificacion.getPublicacion().getId()));
			}
		}
	}

	@Override
	public Notificacion recuperarNotificacion(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notificacion> recuperarTodasNotificaciones() {
		// TODO Auto-generated method stub
		return null;
	}

}
