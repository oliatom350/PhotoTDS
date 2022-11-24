package tds.PhotoTDS.dao;

public abstract class FactoriaDAO {

	private static FactoriaDAO unicaInstancia;
	
	public static FactoriaDAO getFactoriaDAO() {
		return unicaInstancia;
	}
	
	// Crea un tipo de factoria DAO. Solo existe el tipo TDSFactoriaDAO
	public static FactoriaDAO getFactoriaDAO (Integer tipo) throws Exception 
	{
		if (unicaInstancia == null)
				try { 
					unicaInstancia = (FactoriaDAO) Class.forName(tipo.toString()).newInstance();
				} catch (Exception e) {
					throw new Exception(e.getMessage());
				}
		return unicaInstancia;
	}
}
