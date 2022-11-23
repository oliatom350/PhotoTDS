package tds.PhotoTDS;

import java.util.ArrayList;

public class RepoUsuarios {
	
	//Atributos
	private ArrayList<Usuario> usuarios;

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	//Métodos
	public void addUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public Usuario findUsuario(Usuario usuario) {
		int ind = usuarios.indexOf(usuario);
		if(ind == -1)
			return null;
		return usuarios.get(ind);
	}
	
	
	
}
