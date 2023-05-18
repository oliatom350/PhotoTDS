package tds.PhotoTDS;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;

public class TestDescuentos {

	@Test
	public void test() {
		double valor1 = 0.0;
		double valor2 = 1.0;
		//Usuario de edad comprendida entre 18 y 25 años
		try {
			Usuario joven = PhotoTDS.getUnicaInstancia().getUsuario(413);
			HashSet<Descuento> desc = joven.getDescuentos();
			Iterator<Descuento> it = desc.iterator();
			if (it.hasNext()) {
				valor1 = it.next().getPrecioPremium();
				valor2 = Descuento.PRECIO_PREMIUM*0.75;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("", valor1, valor2, 0.01);
		//Usuario de edad superior a 65 años
		try {
			Usuario mayor = PhotoTDS.getUnicaInstancia().getUsuario(401);
			HashSet<Descuento> desc = mayor.getDescuentos();
			Iterator<Descuento> it = desc.iterator();
			if (it.hasNext()) {
				valor1 = it.next().getPrecioPremium();
				valor2 = Descuento.PRECIO_PREMIUM*0.85;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("", valor1, valor2, 0.01);
		//Usuario con publicación con más de 100 MGs
		try {
			Usuario famoso = PhotoTDS.getUnicaInstancia().getUsuario(1);
			HashSet<Descuento> desc = famoso.getDescuentos();
			Iterator<Descuento> it = desc.iterator();
			if (it.hasNext()) {
				valor1 = it.next().getPrecioPremium();
				valor2 = Descuento.PRECIO_PREMIUM*0.8;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("", valor1, valor2, 0.01);
	}

}
