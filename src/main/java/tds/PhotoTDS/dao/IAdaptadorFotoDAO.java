package tds.PhotoTDS.dao;

import java.util.List;

import tds.PhotoTDS.Foto;

public interface IAdaptadorFotoDAO {
	public void registrarFoto(Foto foto);
	public void borrarFoto(Foto foto);
	public void modificarFoto(Foto foto);
	public Foto recuperarFoto(int codigo) throws Exception;
	public List<Foto> recuperarTodasFotos() throws Exception;
}
