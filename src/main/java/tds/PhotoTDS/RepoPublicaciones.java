package tds.PhotoTDS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tds.PhotoTDS.dao.FactoriaDAO;
import tds.PhotoTDS.dao.IAdaptadorFotoDAO;
import tds.PhotoTDS.dao.IAdaptadorTDSAlbumDAO;

public class RepoPublicaciones {

	//Atributos
		private Map<Integer, Publicacion> Publicaciones;
		private static RepoPublicaciones unicaInstancia = new RepoPublicaciones();

		private FactoriaDAO dao;
		private IAdaptadorFotoDAO adaptadorFoto;
		private IAdaptadorTDSAlbumDAO adaptadorAlbum;
		
		public RepoPublicaciones() {
			try {
				 dao = FactoriaDAO.getFactoriaDAO(FactoriaDAO.DAO_TDS);
				 adaptadorFoto = dao.getFotoDAO();
				 adaptadorAlbum = dao.getAlbumDAO();
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
		
		public void addFoto(Foto foto) {
			Publicaciones.put(foto.getId(), foto);
		}
		
		public void addAlbum(Album album) {
			Publicaciones.put(album.getId(), album);
		}
		
		public void removeFoto(Foto foto) {
			Publicaciones.remove(foto.getId());
		}
		
		public void removeAlbum(Album album) {
			Publicaciones.remove(album.getId());
		}
		
		public Foto getFoto(int id) {
			return (Foto) Publicaciones.get(id);
		}
		
		public Album getAlbum(int id) {
			return (Album) Publicaciones.get(id);
		}
		
		public List<Publicacion> getPublicaciones(){
			ArrayList<Publicacion> lista = new ArrayList<Publicacion>();
			for (Publicacion publicacion: Publicaciones.values())
				lista.add(publicacion);
			return lista;
		}
		
		public ArrayList<Foto> getFotos(){
			ArrayList<Foto> lista = new ArrayList<Foto>();
			for (Publicacion p : Publicaciones.values()) {
				if (p instanceof Foto)
					lista.add((Foto)p);
			}
			return lista;
		}
		
		private void cargarRepo() throws Exception {
			List<Foto> FotosBD = adaptadorFoto.recuperarTodasFotos();
			List<Album> AlbumsBD = adaptadorAlbum.recuperarTodasAlbums();
			for (Foto f : FotosBD)
				Publicaciones.put(f.getId(), f);
			for(Album a : AlbumsBD)
				Publicaciones.put(a.getId(), a);
		}
}
