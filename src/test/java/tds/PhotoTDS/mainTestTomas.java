package tds.PhotoTDS;

import tds.PhotoTDS.interfaz.VentanaEntrada;

public class mainTestTomas {

	public static void main(String[] args) throws Exception {

		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		controlador.inicializarAdaptadores();
		controlador.inicializarRepos();
		VentanaEntrada vE = new VentanaEntrada();
		vE.setVisible(true);
		
	}
}
