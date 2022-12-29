package tds.PhotoTDS;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import tds.PhotoTDS.dao.*;

public class mainTest {

	public static void main(String[] args) {
		
		ServicioPersistencia servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		
//		Usuario jose = new Usuario("jose", "jose@umu.es", "jose sanchez fernandez", new Date(2001, 10, 26), false);
//		Publicacion bicho = new Publicacion("elBicho", "hola", null, null);
//		Notificacion hey = new Notificacion(new Date(2001, 10, 26), bicho);
//		Comentario comment = new Comentario("que tal el dia", null);
//		
		AdaptadorTDSUsuarioDAO u = new AdaptadorTDSUsuarioDAO();
		AdaptadorTDSComentarioDAO c = new AdaptadorTDSComentarioDAO();
		AdaptadorTDSPublicacionDAO p = new AdaptadorTDSPublicacionDAO();
		AdaptadorTDSNotificacionDAO n = new AdaptadorTDSNotificacionDAO();
//		u.registrarUsuario(jose);
//		c.registrarComentario(comment);
//		n.registrarNotificacion(hey);
//		p.registrarPublicacion(bicho);
//		
//		System.out.println(servPersistencia.recuperarEntidades().toString());
//		
//		u.borrarUsuario(jose);
//		c.borrarComentario(comment);
//		n.borrarNotificacion(hey);
//		p.borrarPublicacion(bicho);
		System.out.println(servPersistencia.recuperarEntidades().toString());
		System.out.println(u.recuperarTodosUsuarios().toString());
		System.out.println(c.recuperarTodosComentarios().toString());
		System.out.println(n.recuperarTodasNotificaciones().toString());
		System.out.println(p.recuperarTodasPublicaciones().toString());
	}

}
