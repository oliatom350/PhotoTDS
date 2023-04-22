package tds.PhotoTDS;

import java.util.ArrayList;

public class DescuentoPopularidad implements Descuento {

	@Override
	public double getPrecioPremium(Usuario usuario, double precioPremium) {
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		ArrayList<Foto> publicaciones = new ArrayList<Foto>();
		try {
			publicaciones = controlador.getFotosUsuario(usuario.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int numMG = publicaciones.stream()
					.mapToInt(p -> p.getMeGusta())
					.sum();
		
		//Si un usuario tiene entre 75 y 150 "me gusta" tiene un 10% de descuento
		if (numMG >= 75 && numMG <= 150) return precioPremium*0.9;
		//Si un usuario tiene más de 150 "me gusta" tiene un 20% de descuento
		if (numMG > 150) return precioPremium*0.8;
		//Si no cumple ninguno, no se aplica descuento ninguno
		return precioPremium;
	}
}
