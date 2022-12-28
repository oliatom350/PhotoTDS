package tds.PhotoTDS;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class mainTest {

	public static void main(String[] args) {
		
		ServicioPersistencia servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		
		Entidad usuario = new Entidad();

		usuario.setNombre("Tomas");
		usuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("nif" , "12345"))));
		//servPersistencia.registrarEntidad(usuario);
		
		System.out.println(servPersistencia.recuperarEntidades().toString());
		
		servPersistencia.borrarEntidad(usuario);
		
				for(Entidad entidad : servPersistencia.recuperarEntidades()) {
			servPersistencia.borrarEntidad(entidad);
		}
		
		System.out.println(servPersistencia.recuperarEntidades().toString());
				
	}

}
