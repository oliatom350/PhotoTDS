package tds.PhotoTDS;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class DescuentoEdad implements Descuento {

	@Override
	public double getPrecioPremium(Usuario usuario, double precioPremium) {
		LocalDate now = LocalDate.now();
		LocalDate birthday = usuario.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int edad = Period.between(birthday, now).getYears();
		//Si es menor de 18 años, tiene un 50% de descuento
		if (edad < 18) {
			return precioPremium * 0.5;
		}
		//Si tiene entre 18 y 30 años, o bien 60 años o más, tiene un 25% de descuento
		if ((edad >= 18 && edad <= 30) || (edad >= 60)) {
			return precioPremium * 0.75;
		}
		//Si tiene entre 31 y 59 años, no tiene descuento
		return precioPremium;
	}
}
