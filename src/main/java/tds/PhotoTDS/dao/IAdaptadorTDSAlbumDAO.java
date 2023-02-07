package tds.PhotoTDS.dao;

import java.util.List;

import tds.PhotoTDS.Album;

public interface IAdaptadorTDSAlbumDAO {
	public void registrarAlbum(Album album);
	public void borrarAlbum(Album album);
	public void modificarAlbum(Album f);
	public Album recuperarAlbum(int codigo) throws Exception;
	public List<Album> recuperarTodasAlbums() throws Exception;
}
