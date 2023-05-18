package tds.PhotoTDS;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

public class TestAddMeGustaAlbum {

	@Test
	public void test() {
		LinkedList<Foto> fotos = new LinkedList<Foto>();

		Foto fprueba = new Foto("titulo", "descripcion", new ArrayList<String>(), 2, "prueba");
		Foto fprueba2 = new Foto("titulo", "descripcion", new ArrayList<String>(), 2, "prueba");
		Foto fprueba3 = new Foto("titulo", "descripcion", new ArrayList<String>(), 2, "prueba");
		fotos.add(fprueba);
		fotos.add(fprueba2);
		fotos.add(fprueba3);
		
		Album albumResultado = new Album("titulo", "descripcion", new ArrayList<String>(), 2, fotos);
		albumResultado.addMeGusta();

		boolean addMeGusta = true;
		for(Foto f : albumResultado.getFotos()) {
			if(f.getMeGusta() != 1) {
				addMeGusta = false;
			}
		}
		
		assertEquals("El resultado de añadir un me gusta a un álbum: ", addMeGusta, true);
	}

}
