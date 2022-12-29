package tds.PhotoTDS.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.PhotoTDS.Notificacion;
import tds.PhotoTDS.Publicacion;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorTDSNotificacionDAO implements IAdaptadorNotificacionDAO{

	ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private PoolDAO poolNotificaciones;
	
	public AdaptadorTDSNotificacionDAO() {
		poolNotificaciones = PoolDAO.getInstance();
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
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
						new Propiedad("fecha", dateFormat.format(notificacion.getFecha())),
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
	public void modificarNotificacion(Notificacion notificacion) {
		Entidad eNotificacion = servPersistencia.recuperarEntidad(notificacion.getId());
		
		for (Propiedad propiedad : eNotificacion.getPropiedades()) {
			if(propiedad.getNombre().equals("fecha")) {
				propiedad.setValor(notificacion.getFecha().toString());
			}
			if(propiedad.getNombre().equals("publicacion")) {
				propiedad.setValor(Integer.toString(notificacion.getPublicacion().getId()));
			}
		}
		servPersistencia.modificarEntidad(eNotificacion);
	}

	@Override
	public Notificacion recuperarNotificacion(int id) {
		if (poolNotificaciones.contains(id)) return (Notificacion) poolNotificaciones.getObject(id);
		
		Entidad eNotificacion = servPersistencia.recuperarEntidad(id);
		
		Date fecha = null;
		//TODO 
		// Realmente hace falta este IF?
		String f = servPersistencia.recuperarPropiedadEntidad(eNotificacion, "fechaNacimiento");
		if (f != null) {
			try {
				fecha = dateFormat.parse(f);
			} catch (ParseException e) {e.printStackTrace(); }
		}
		//TODO
		//Cannot invoke "tds.PhotoTDS.dao.FactoriaDAO.getPublicacionDAO()" because the return value of "tds.PhotoTDS.dao.FactoriaDAO.getFactoriaDAO()" is null
		//Al no haber publicaciones, no se recupera nada y da error
		Publicacion publicacion = FactoriaDAO.getFactoriaDAO().getPublicacionDAO().recuperarPublicacion(Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eNotificacion, "publicacion")));
		
		Notificacion notificacion = new Notificacion(fecha, publicacion);
		
		return notificacion;
	}

	@Override
	public List<Notificacion> recuperarTodasNotificaciones() {
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		List<Entidad> eNotificaciones = servPersistencia.recuperarEntidades("notificacion");
		for(Entidad e : eNotificaciones) {
			notificaciones.add(recuperarNotificacion(e.getId()));
		}
		return notificaciones;
	}

}
