package tds.PhotoTDS;

import java.util.ArrayList;
import java.util.List;

public class RepoPublicaciones {

	//Atributos
		private ArrayList<Publicacion> publicaciones;

		public ArrayList<Publicacion> getpublicaciones() {
			return publicaciones;
		}

		public void setPublicaciones(List<Publicacion> publicaciones) {
			this.publicaciones = (ArrayList<Publicacion>) publicaciones;
		}
		
		//Métodos
		public void addPublicacion(Publicacion Publicacion) {
			publicaciones.add(Publicacion);
		}
		
		public Publicacion findPublicacion(int id) {
			for(Publicacion p : publicaciones) {
				if(id == p.getId()) {
					return p;
				}
			}
			return null;
		}
	
}
