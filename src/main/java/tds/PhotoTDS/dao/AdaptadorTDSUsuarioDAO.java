package tds.PhotoTDS.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.PhotoTDS.Notificacion;
import tds.PhotoTDS.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorTDSUsuarioDAO implements IAdaptadorUsuarioDAO{
	
	private ServicioPersistencia servPersistencia;
	
	public AdaptadorTDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		
	}

	@Override
	public void registrarUsuario(Usuario usuario) {
		boolean existe = false;
		Entidad eUsuario = null;
		try {
			eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		} catch (NullPointerException e){
			existe = true;
		}
		if (existe || eUsuario != null) return;
		
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");
		
		eUsuario.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						new Propiedad("nombre", usuario.getNombre()),
						new Propiedad("email", usuario.getEmail()),
						new Propiedad("nombreCompleto", usuario.getNombreCompleto()),
						new Propiedad("fechaNacimiento", usuario.getFechaNacimiento().toString()),
						new Propiedad("isPremium", String.valueOf(usuario.isPremium())),
						new Propiedad("usuariosSeguidores", AuxiliarDAO.obtenerCadenaDeIdsUsuario(usuario.getUsuariosSeguidores())),
						new Propiedad("notificaciones", AuxiliarDAO.obtenerIdsNotificaciones(usuario.getNotificaciones())))));
		
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		
		//Aqui se eliminarian las entidades agregadas, pero como dijimos de hacerlo separado no hacemos nada, despúes haremos funciones auxiliares
		servPersistencia.borrarEntidad(eUsuario);
		
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		for(Propiedad propiedad : eUsuario.getPropiedades()) {
			if(propiedad.getNombre().equals("nombre")) {
				propiedad.setValor(usuario.getNombre());
			}
			if(propiedad.getNombre().equals("email")) {
				propiedad.setValor(usuario.getEmail());
			}
			if(propiedad.getNombre().equals("nombreCompleto")) {
				propiedad.setValor(usuario.getNombreCompleto());
			}
			if(propiedad.getNombre().equals("fechaNacimiento")) {
				propiedad.setValor(usuario.getNombre());
			}
			if(propiedad.getNombre().equals("isPremium")) {
				propiedad.setValor(String.valueOf(usuario.isPremium()));
			}
			if(propiedad.getNombre().equals("usuariosSeguidores")) {
				propiedad.setValor(AuxiliarDAO.obtenerCadenaDeIdsUsuario(usuario.getUsuariosSeguidores()));
			}
			if(propiedad.getNombre().equals("notificaciones")) {
				propiedad.setValor(AuxiliarDAO.obtenerIdsNotificaciones(usuario.getNotificaciones()));
			}
		}
	}

	@Override
	public Usuario recuperarUsuario(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
