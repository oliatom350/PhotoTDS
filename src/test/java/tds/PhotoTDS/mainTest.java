package tds.PhotoTDS;

import java.util.ArrayList;

import tds.PhotoTDS.interfaz.VentanaEntrada;

public class mainTest {

	public static void main(String[] args) throws Exception {

		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		controlador.inicializarAdaptadores();
		controlador.inicializarRepos();
		VentanaEntrada vE = new VentanaEntrada();
		vE.setVisible(true);

	}
}
