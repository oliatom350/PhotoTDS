package tds.PhotoTDS;

public class DescuentoPopularidad implements Descuento {

	@Override
	public double getPrecioPremium() {
		return Descuento.PRECIO_PREMIUM * 0.8;
	}
}
