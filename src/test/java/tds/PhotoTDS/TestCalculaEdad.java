package tds.PhotoTDS;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;

public class TestCalculaEdad {

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		int yfechaN = 2001;
		Usuario usuario = new Usuario("", "", "Jose Antonio", new Date(yfechaN-1900, 10, 26), false, "", "", "");
		int resultado = usuario.calculaEdad();
		int resultadoEsperado = 21;
		assertEquals("Resultado de calcular edad: ", resultadoEsperado, resultado);
	}

}
