package tds.PhotoTDS.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import tds.PhotoTDS.Comentario;
import tds.PhotoTDS.Notificacion;
import tds.PhotoTDS.Publicacion;

public class AuxiliarDAO {

	//Métodos auxiliares para los adaptadores DAO
	
	public static String obtenerIdsNotificaciones(List<Notificacion> notificaciones) {
		String lineas = "";
		for(Notificacion notificacion : notificaciones)
			lineas += notificacion.getId() + " ";
		return lineas.trim();
	}
	
	public List<Notificacion> obtenerNotificacionesDesdeIds(String lineas) {
		List<Notificacion> notificaciones = new LinkedList<Notificacion>(); 
		StringTokenizer strTok = new StringTokenizer(lineas, " ");
		while (strTok.hasMoreTokens()) {
			notificaciones.add(FactoriaDAO.getFactoriaDAO().getNotificacionDAO().recuperarNotificacion(Integer.valueOf((String)strTok.nextElement())));
		}
		return notificaciones;
	}
	
	public static String obtenerCadenaDeIdsUsuario(List<String> usuarios) {
		String lineas = "";
		for(String linea : usuarios) {
			lineas += linea + " ";
		}
		return lineas.trim();
	}
	
	public static List<String> obtenerUsuariosDesdeIds(String lineas) {
		List<String> usuarios = new LinkedList<String>();
		StringTokenizer strTok = new StringTokenizer(lineas, " ");
		while (strTok.hasMoreTokens()) {
			usuarios.add((String)strTok.nextElement());
			//usuarios.add(FactoriaDAO.getFactoriaDAO().getUsuarioDAO().recuperarTodosUsuarios(Integer.valueOf((String)strTok.nextElement())));
		}
		return usuarios;
	}
	
	public static String obtenerIdsComentarios(List<Comentario> comentarios) {
		String lineas = "";
		for(Comentario comentario : comentarios)
			lineas += comentario.getId() + " ";
		return lineas.trim();
	}
	
//	public String obtenerComentariosDesdeIds(String lineas) {
//		List<Comentario> comentarios = new LinkedList<Comentario>(); 
//		StringTokenizer strTok = new StringTokenizer(lineas, " ");
//		while (strTok.hasMoreTokens()) {
//			comentarios.add(FactoriaDAO.getFactoriaDAO().getComentario().recuperarComentario(Integer.valueOf((String)strTok.nextElement())));
//		}
//	}
}
