package tds.PhotoTDS.dao;

public class TDSFactoriaDAO extends FactoriaDAO{

	public TDSFactoriaDAO() {}
	
	@Override
	public IAdaptadorComentarioDAO getComentarioDAO() {
		return AdaptadorTDSComentarioDAO.getUnicaInstancia();
	}

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorTDSUsuarioDAO.getUnicaInstancia();
	}

	@Override
	public IAdaptadorNotificacionDAO getNotificacionDAO() {
		return AdaptadorTDSNotificacionDAO.getUnicaInstancia();
	}
	
	@Override
	public IAdaptadorFotoDAO getFotoDAO() {
		return AdaptadorTDSFotoDAO.getUnicaInstancia();
	}
	
	@Override
	public IAdaptadorTDSAlbumDAO getAlbumDAO() {
		return AdaptadorTDSAlbumDAO.getUnicaInstancia();
	}
}
