package tds.PhotoTDS.dao;

public abstract class FactoriaDAO {

	private static FactoriaDAO unicaInstancia;
	
	public static FactoriaDAO getFactoriaDAO() {
		return unicaInstancia;
	}
	
	// Crea un tipo de factoria DAO. Solo existe el tipo TDSFactoriaDAO
	@SuppressWarnings("deprecation")
	public static FactoriaDAO getFactoriaDAO (String tipo) throws Exception 
	{
		if (unicaInstancia == null)
				try { 
					unicaInstancia = (FactoriaDAO) Class.forName(tipo).newInstance();
				} catch (Exception e) {
					throw new Exception(e.getMessage());
				}
		return unicaInstancia;
	}
	
	protected FactoriaDAO () {}
	
	public abstract IAdaptadorUsuarioDAO getUsuarioDAO();
	public abstract IAdaptadorNotificacionDAO getNotificacion();
	public abstract IAdaptadorPublicacionDAO getPublicacion();
	public abstract IAdaptadorComentarioDAO getPublicacion();
	
}
