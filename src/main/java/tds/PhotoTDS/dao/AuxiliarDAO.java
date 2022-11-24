package tds.PhotoTDS.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import tds.PhotoTDS.Comentario;
import tds.PhotoTDS.Notificacion;

public class AuxiliarDAO {

	//Métodos auxiliares para los adaptadores DAO
	
	public static String obtenerIdsNotificaciones(List<Notificacion> notificaciones) {
		String lineas = "";
		for(Notificacion notificacion : notificaciones)
			lineas += notificacion.getId() + " ";
		return lineas.trim();
	}
	
	public String obtenerNotificacionesDesdeIds(String lineas) {
		List<Notificacion> notificaciones = new LinkedList<Notificacion>(); 
		StringTokenizer strTok = new StringTokenizer(lineas, " ");
		while (strTok.hasMoreTokens()) {
			notificaciones.add(FactoriaDAO.getFactoriaDAO().getNotificacion().recuperarTodasNotificaciones(Integer.valueOf((String)strTok.nextElement())));
		}
	}
	
	public static String obtenerIdsComentarios(List<Comentario> comentarios) {
		String lineas = "";
		for(Comentario comentario : comentarios)
			lineas += comentario.getId() + " ";
		return lineas.trim();
	}
	
	public String obtenerComentariosDesdeIds(String lineas) {
		List<Comentario> comentarios = new LinkedList<Comentario>(); 
		StringTokenizer strTok = new StringTokenizer(lineas, " ");
		while (strTok.hasMoreTokens()) {
			comentarios.add(FactoriaDAO.getFactoriaDAO().getComentario().recuperarTodosComentarios(Integer.valueOf((String)strTok.nextElement())));
		}
	}
}
