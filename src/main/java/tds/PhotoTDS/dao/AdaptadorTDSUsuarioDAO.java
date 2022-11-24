package tds.PhotoTDS.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import beans.Entidad;
import tds.PhotoTDS.Usuario;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorTDSUsuarioDAO implements IAdaptadorUsuarioDAO{
	
	private ServicioPersistencia servPersistencia;
	
	public AdaptadorTDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		
	}

	@Override
	public Usuario registrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		try {
			eUsuario = servPersistencia.recuperarEntidad(usuario.get)
		}
		return null;
	}

	@Override
	public void borrarUsuario(Usuario Usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario modificarUsuario(Usuario Usuario) {
		// TODO Auto-generated method stub
		return null;
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
