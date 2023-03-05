package tds.PhotoTDS.dao;

import java.util.List;
import tds.PhotoTDS.Publicacion;

public interface IAdaptadorPublicacionDAO {
	public void registrarPublicacion(Publicacion publicacion);
	public void borrarPublicacion(Publicacion publicacion);
	public void modificarPublicacion(Publicacion publicacion);
	public Publicacion recuperarPublicacion(int codigo) throws Exception;
	public List<Publicacion> recuperarTodasPublicaciones() throws Exception;
}
