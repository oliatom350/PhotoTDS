package tds.PhotoTDS;

import java.util.ArrayList;

import tds.PhotoTDS.interfaz.VentanaEntrada;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class mainTest {

	public static void main(String[] args) throws Exception {

		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		ArrayList<Publicacion> p = (ArrayList<Publicacion>) controlador.getRepPubli().getPublicaciones();
		VentanaEntrada vE = new VentanaEntrada();
		vE.setVisible(true);

	}
}
