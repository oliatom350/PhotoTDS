package tds.PhotoTDS.dao;

import java.util.List;

import tds.PhotoTDS.Usuario;
import tds.PhotoTDS.Notificacion;

public interface IAdaptadorNotificacionDAO {
	public void registrarNotificacion(Notificacion notificacion);
	public void borrarNotificacion(Notificacion notificacion);
	public void modificarUsuario(Notificacion notificacion);
	public Notificacion recuperarNotificacion(int id);
	public List<Notificacion> recuperarTodasNotificaciones();
}
