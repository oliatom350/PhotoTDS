package tds.PhotoTDS.dao;

import java.util.List;

import tds.PhotoTDS.Usuario;

public interface IAdaptadorUsuarioDAO {
	public void registrarUsuario(Usuario Usuario);
	public void borrarUsuario(Usuario Usuario);
	public void modificarUsuario(Usuario Usuario);
	public Usuario recuperarUsuario(int codigo);
	public List<Usuario> recuperarTodosUsuarios();
}
