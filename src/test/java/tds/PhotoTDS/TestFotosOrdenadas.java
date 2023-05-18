package tds.PhotoTDS;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class TestFotosOrdenadas {

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		ArrayList<Foto> fotosResultadoEsperado = new ArrayList<Foto>();
		ArrayList<Foto> fotosResultado = new ArrayList<Foto>();
		Foto fprueba = new Foto("titulo", "descripcion", new ArrayList<String>(), 2, "prueba");
		fprueba.setFecha(new Date(2023, 1, 1));
		Foto fprueba2 = new Foto("titulo", "descripcion", new ArrayList<String>(), 2, "prueba");
		fprueba2.setFecha(new Date(2023, 1, 2));
		Foto fprueba3 = new Foto("titulo", "descripcion", new ArrayList<String>(), 2, "prueba");
		fprueba3.setFecha(new Date(2023, 1, 3));
		fotosResultadoEsperado.add(fprueba);
		fotosResultadoEsperado.add(fprueba2);
		fotosResultadoEsperado.add(fprueba3);
		fotosResultado.add(fprueba3);
		fotosResultado.add(fprueba2);
		fotosResultado.add(fprueba);
		
		assertEquals("Resultado de prueba al ordenar fotos: ", fotosResultadoEsperado, Foto.getFotosOrdenadas(fotosResultado));
		assertNotEquals("Resultado de prueba al ordenar fotos: ", fotosResultado, Foto.getFotosOrdenadas(fotosResultado));
	}

}
