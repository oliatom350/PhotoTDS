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
import tds.PhotoTDS.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorTDSUsuarioDAO implements IAdaptadorUsuarioDAO{
	
	private ServicioPersistencia servPersistencia;
	private PoolDAO poolUsuarios;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static AdaptadorTDSUsuarioDAO unicaInstancia;
	
	public AdaptadorTDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		poolUsuarios = PoolDAO.getInstance();
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
						new Propiedad("fechaNacimiento", dateFormat.format(usuario.getFechaNacimiento())),
						new Propiedad("isPremium", String.valueOf(usuario.isPremium())),
						new Propiedad("usuariosSeguidores", AuxiliarDAO.obtenerCadenaDeIds(usuario.getUsuariosSeguidores())),
						new Propiedad("usuariosSeguidos", AuxiliarDAO.obtenerCadenaDeIds(usuario.getUsuariosSeguidos())),
						new Propiedad("notificaciones", AuxiliarDAO.obtenerIdsNotificaciones(usuario.getNotificaciones())),
						new Propiedad("password", usuario.getPassword()),
						new Propiedad("fotoPerfil", usuario.getFotoPerfil()),
						new Propiedad("presentacion", usuario.getPresentacion()))));
		
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		
		//Aqui se eliminarian las entidades agregadas, pero como dijimos de hacerlo separado no hacemos nada, desp??es haremos funciones auxiliares
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
				propiedad.setValor(usuario.getFechaNacimiento().toString());
			}
			if(propiedad.getNombre().equals("isPremium")) {
				propiedad.setValor(String.valueOf(usuario.isPremium()));
			}
			if(propiedad.getNombre().equals("usuariosSeguidores")) {
				propiedad.setValor(AuxiliarDAO.obtenerCadenaDeIds(usuario.getUsuariosSeguidores()));
			}
			if(propiedad.getNombre().equals("notificaciones")) {
				propiedad.setValor(AuxiliarDAO.obtenerIdsNotificaciones(usuario.getNotificaciones()));
			}
			if(propiedad.getNombre().equals("usuariosSeguidos")) {
				propiedad.setValor(AuxiliarDAO.obtenerCadenaDeIds(usuario.getUsuariosSeguidos()));
			}
			if(propiedad.getNombre().equals("password")) {
				propiedad.setValor(usuario.getPassword());
			}
			if(propiedad.getNombre().equals("fotoPerfil")) {
				propiedad.setValor(usuario.getFotoPerfil());
			}
			if(propiedad.getNombre().equals("presentacion")) {
				propiedad.setValor(usuario.getPresentacion());
			}
		}
		servPersistencia.modificarEntidad(eUsuario);
	}

	@Override
	public Usuario recuperarUsuario(int codigo) throws Exception {
		if (poolUsuarios.contains(codigo)) return (Usuario) poolUsuarios.getObject(codigo);
		
		Date fecha = null;
		
		
		Entidad eUsuario = servPersistencia.recuperarEntidad(codigo);
		try {
			fecha = dateFormat.parse(servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechaNacimiento"));
		} catch (ParseException e) {e.printStackTrace(); }
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		String nombreCompleto = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombreCompleto");
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, "password");
		String fotoPerfil = servPersistencia.recuperarPropiedadEntidad(eUsuario, "fotoPerfil");
		String presentacion = servPersistencia.recuperarPropiedadEntidad(eUsuario, "presentacion");
		boolean isPremium = Boolean.parseBoolean(servPersistencia.recuperarPropiedadEntidad(eUsuario, "isPremium"));
		List<String> usuariosSeguidores = AuxiliarDAO.obtenerListaDeIds(servPersistencia.recuperarPropiedadEntidad(eUsuario, "usuariosSeguidores"));
		List<Notificacion> notificaciones = AuxiliarDAO.obtenerNotificacionesDesdeIds(servPersistencia.recuperarPropiedadEntidad(eUsuario, "notificaciones"));
		List<String> usuariosSeguidos = AuxiliarDAO.obtenerListaDeIds(servPersistencia.recuperarPropiedadEntidad(eUsuario, "usuariosSeguidos"));
		
		Usuario usuario = new Usuario(nombre, email, nombreCompleto, fecha, isPremium, password, fotoPerfil, presentacion);
		for(String usuarioId : usuariosSeguidores) {
			usuario.addSeguidor(usuarioId);
		}
		for(Notificacion notificacion : notificaciones) {
			usuario.addNotificacion(notificacion);
		}
		for(String usuarioId : usuariosSeguidos) {
			usuario.addSeguidor(usuarioId);
		}
		
		
		poolUsuarios.addObject(codigo, usuario);
		
		return usuario;
	}

	@Override
	public ArrayList<Usuario> recuperarTodosUsuarios() throws Exception {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		List<Entidad> eUsuarios= servPersistencia.recuperarEntidades("usuario");
		for(Entidad e : eUsuarios) {
			usuarios.add(recuperarUsuario(e.getId()));
		}
		return usuarios;
	}

	public static AdaptadorTDSUsuarioDAO getUnicaInstancia() {
		if (unicaInstancia == null)
			 return new AdaptadorTDSUsuarioDAO();
		else
			 return unicaInstancia; 
	}
	
	
}
