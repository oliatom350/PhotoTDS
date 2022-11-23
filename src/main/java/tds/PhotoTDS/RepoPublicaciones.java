package tds.PhotoTDS;

import java.util.ArrayList;

public class RepoPublicaciones {

	//Atributos
		private ArrayList<Publicacion> publicaciones;

		public ArrayList<Publicacion> getpublicaciones() {
			return publicaciones;
		}

		//Métodos
		public void addPublicacion(Publicacion Publicacion) {
			publicaciones.add(Publicacion);
		}
		
		public Publicacion findPublicacion(Publicacion Publicacion) {
			int ind = publicaciones.indexOf(Publicacion);
			if(ind == -1)
				return null;
			return publicaciones.get(ind);
		}
	
}
