package tds.PhotoTDS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tds.PhotoTDS.dao.FactoriaDAO;
import tds.PhotoTDS.dao.IAdaptadorPublicacionDAO;

public class RepoPublicaciones {

	//Atributos
		private Map<Integer, Publicacion> Publicaciones;
		private static RepoPublicaciones unicaInstancia = new RepoPublicaciones();

		private FactoriaDAO dao;
		private IAdaptadorPublicacionDAO adaptadorPublicacion;
		
		public RepoPublicaciones() {
			try {
				 dao = FactoriaDAO.getFactoriaDAO(FactoriaDAO.DAO_TDS);
				 adaptadorPublicacion = dao.getPublicacionDAO();
				 Publicaciones = new HashMap<Integer,Publicacion>();
				 this.cargarRepo();
			} catch (Exception eDAO) {
				 eDAO.printStackTrace();
			} 
		}
		
		public static RepoPublicaciones getInstancia() {
			return unicaInstancia;
		}
		
		public void addPublicacion(Publicacion Publicacion) {
			 Publicaciones.put(Publicacion.getId(), Publicacion);
		}
		
		public void removePublicacion(Publicacion Publicacion) {
			 Publicaciones.remove(Publicacion.getId());
		}
		
		public Publicacion getPublicacion(int id) {
			return Publicaciones.get(id);
		}
		
		private void cargarRepo() {
			List<Publicacion> PublicacionesBD = adaptadorPublicacion.recuperarTodasPublicaciones();
			for (Publicacion Publicacion: PublicacionesBD)
				Publicaciones.put(Publicacion.getId(), Publicacion);			 
		}
}
