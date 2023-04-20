package tds.PhotoTDS;

import java.util.ArrayList;

import tds.PhotoTDS.interfaz.VentanaEntrada;

public class mainTestTomas {

	public static void main(String[] args) throws Exception {

		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		controlador.inicializarAdaptadores();
		controlador.inicializarRepos();
		ArrayList<Publicacion> fotos = (ArrayList<Publicacion>) controlador.getRepPubli().getPublicaciones();
		VentanaEntrada vE = new VentanaEntrada();
		vE.setVisible(true);
		
	}
}
