package tds.PhotoTDS;

import java.util.ArrayList;
import java.util.List;

public class RepoUsuarios {
	
	//Atributos
	private ArrayList<Usuario> usuarios;

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> list) {
		this.usuarios = (ArrayList<Usuario>) list;
	}

	//Métodos
	public void addUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public Usuario findUsuario(int id) {
		for(Usuario u : usuarios) {
			if(id == u.getId()) {
				return u;
			}
		}
		return null;
	}
	
	
	
}
