package tds.PhotoTDS;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import tds.PhotoTDS.interfaz.VentanaEntrada;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class mainTest {

	public static void main(String[] args) throws Exception {

		Date date = new Date();  
	    System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date));  
		
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		
		ArrayList<Publicacion> p = (ArrayList<Publicacion>) controlador.getRepPubli().getPublicaciones();
		VentanaEntrada vE = new VentanaEntrada();
		vE.setVisible(true);

	}
}
