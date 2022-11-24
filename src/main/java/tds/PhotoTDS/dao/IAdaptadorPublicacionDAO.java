package tds.PhotoTDS.dao;

import java.util.List;
import tds.PhotoTDS.Publicacion;

public interface IAdaptadorPublicacionDAO {
	public void registrarPublicacion(Publicacion publicacion);
	public void borrarPublicacion(Publicacion publicacion);
	public Publicacion modificarPublicacion(Publicacion publicacion);
	public Publicacion recuperarPublicacion(int codigo);
	public List<Publicacion> recuperarTodasPublicaciones();
}
