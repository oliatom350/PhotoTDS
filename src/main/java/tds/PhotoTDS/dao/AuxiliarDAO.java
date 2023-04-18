package tds.PhotoTDS.dao;

import java.util.ArrayList;
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
	
	public static List<Notificacion> obtenerNotificacionesDesdeIds(String lineas) throws Exception {
		List<Notificacion> notificaciones = new LinkedList<Notificacion>(); 
		StringTokenizer strTok = new StringTokenizer(lineas, " ");
		while (strTok.hasMoreTokens()) {
			notificaciones.add(FactoriaDAO.getFactoriaDAO().getNotificacionDAO().recuperarNotificacion(Integer.valueOf((String)strTok.nextElement())));
		}
		return notificaciones;
	}
	
	public static String obtenerCadenaDeIds(List<String> ids) {
		String lineas = "";
		if (ids != null) {
			for(String linea : ids) {
				lineas += linea + " ";
			}
		}
		return lineas.trim();
	}
	
	public static String obtenerCadenaDeIdsInt(List<Integer> ids) {
		String lineas = "";
		if (ids != null) {
			for(int linea : ids) {
				lineas += linea + " ";
			}
		}
		return lineas.trim();
	}
	
	public static List<String> obtenerListaDeIds(String lineas) {
		List<String> ids = new ArrayList<String>();
		if(lineas != null) {
			StringTokenizer strTok = new StringTokenizer(lineas, " ");
			while (strTok.hasMoreTokens()) {
				ids.add((String)strTok.nextElement());
				//usuarios.add(FactoriaDAO.getFactoriaDAO().getUsuarioDAO().recuperarTodosUsuarios(Integer.valueOf((String)strTok.nextElement())));
			}
		}
		return ids;

	}
	
	public static List<Integer> obtenerListaDeIdsInt(String lineas) {
		List<Integer> ids = new ArrayList<Integer>();
		if(lineas != null) {
			StringTokenizer strTok = new StringTokenizer(lineas, " ");
			while (strTok.hasMoreTokens()) {
				ids.add(Integer.parseInt((String)strTok.nextElement()));
				//usuarios.add(FactoriaDAO.getFactoriaDAO().getUsuarioDAO().recuperarTodosUsuarios(Integer.valueOf((String)strTok.nextElement())));
			}
		}
		return ids;

	}
	
	public static String obtenerIdsComentarios(List<Comentario> comentarios) {
		String lineas = "";
		for(Comentario comentario : comentarios)
			lineas += comentario.getId() + " ";
		return lineas.trim();
	}
	
	public static List<Comentario> obtenerComentariosDesdeIds(String lineas) throws Exception {
		List<Comentario> comentarios = new ArrayList<Comentario>(); 
		StringTokenizer strTok = new StringTokenizer(lineas, " ");
		while (strTok.hasMoreTokens()) {
			comentarios.add(FactoriaDAO.getFactoriaDAO().getComentarioDAO().recuperarComentario(Integer.valueOf((String)strTok.nextElement())));
		}
		return comentarios;
	}
}
