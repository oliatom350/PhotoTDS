package tds.PhotoTDS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tds.PhotoTDS.dao.FactoriaDAO;
import tds.PhotoTDS.dao.IAdaptadorUsuarioDAO;

public class RepoUsuarios {
	
	//Atributos
	private Map<Integer, Usuario> usuarios;
	private static RepoUsuarios unicaInstancia = new RepoUsuarios();

	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	
	public RepoUsuarios() {
		try {
			 dao = FactoriaDAO.getFactoriaDAO(FactoriaDAO.DAO_TDS);
			 adaptadorUsuario = dao.getUsuarioDAO();
			 usuarios = new HashMap<Integer,Usuario>();
			 this.cargarRepo();
		} catch (Exception eDAO) {
			 eDAO.printStackTrace();
		} 
	}
	
	public static RepoUsuarios getInstancia() {
		return unicaInstancia;
	}
	
	public void addUsuario(Usuario usuario) {
		 usuarios.put(usuario.getId(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		 usuarios.remove(usuario.getId());
	}
	
	public Usuario getUsuario(int id) {
		return usuarios.get(id);
	}
	
	public List<Usuario> getUsuarios(){
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		for (Usuario usuario: usuarios.values())
			lista.add(usuario);
		return lista;
	}
	
	private void cargarRepo() throws Exception {
		List<Usuario> usuariosBD = adaptadorUsuario.recuperarTodosUsuarios();
		for (Usuario usuario: usuariosBD)
			usuarios.put(usuario.getId(), usuario);			 
	}
	
}
