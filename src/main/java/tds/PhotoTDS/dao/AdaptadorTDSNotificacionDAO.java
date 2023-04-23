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
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorTDSNotificacionDAO implements IAdaptadorNotificacionDAO{

	ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	private PoolDAO poolNotificaciones;
	private static AdaptadorTDSNotificacionDAO unicaInstancia;
	
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
						new Propiedad("publicacion", Integer.toString(notificacion.getPublicacion())),
						new Propiedad("mensaje", notificacion.getMensaje())
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
				propiedad.setValor(dateFormat.format(notificacion.getFecha()));
			}
			if(propiedad.getNombre().equals("publicacion")) {
				propiedad.setValor(Integer.toString(notificacion.getPublicacion()));
			}
			if(propiedad.getNombre().equals("mensaje")) {
				propiedad.setValor(notificacion.getMensaje());
			}
			servPersistencia.modificarPropiedad(propiedad);
		}
		//servPersistencia.modificarEntidad(eNotificacion);
	}

	@Override
	public Notificacion recuperarNotificacion(int id) {
		if (poolNotificaciones.contains(id)) return (Notificacion) poolNotificaciones.getObject(id);
		
		Entidad eNotificacion = servPersistencia.recuperarEntidad(id);
		
		Date fecha = null;
		
		String f = servPersistencia.recuperarPropiedadEntidad(eNotificacion, "fecha");
		if (f != null) {
			try {
				fecha = dateFormat.parse(f);
			} catch (ParseException e) {e.printStackTrace(); }
		}
		
		int publicacion = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eNotificacion, "publicacion"));
		String mensaje = servPersistencia.recuperarPropiedadEntidad(eNotificacion, "mensaje");
		
		Notificacion notificacion = new Notificacion(fecha, publicacion, mensaje);
		notificacion.setId(id);
		
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

	public static AdaptadorTDSNotificacionDAO getUnicaInstancia() {
		if (unicaInstancia == null)
			 return new AdaptadorTDSNotificacionDAO();
		else
			 return unicaInstancia; 
	}

}
