package tds.PhotoTDS.dao;

public abstract class FactoriaDAO {

	private static FactoriaDAO unicaInstancia;
	public static final String DAO_TDS = "persistencia.TDSFactoriaDAO";
	
	public static FactoriaDAO getFactoriaDAO() throws Exception {
		if (unicaInstancia == null)
			return getFactoriaDAO(FactoriaDAO.DAO_TDS);
		else return unicaInstancia; 
	}
	
	// Crea un tipo de factoria DAO. Solo existe el tipo TDSFactoriaDAO
	public static FactoriaDAO getFactoriaDAO (String tipo) throws Exception 
	{
		if (unicaInstancia == null)
				try { 
					unicaInstancia = (FactoriaDAO) Class.forName(tipo).getDeclaredConstructor().newInstance();
				} catch (Exception e) {
					throw new Exception(e.getMessage());
				}
		return unicaInstancia;
	}
	
	protected FactoriaDAO () {}
	
	public abstract IAdaptadorUsuarioDAO getUsuarioDAO();
	public abstract IAdaptadorNotificacionDAO getNotificacionDAO();
	public abstract IAdaptadorPublicacionDAO getPublicacionDAO();
	public abstract IAdaptadorComentarioDAO getComentarioDAO();
	
}
