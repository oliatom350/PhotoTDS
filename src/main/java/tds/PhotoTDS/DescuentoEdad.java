package tds.PhotoTDS;

public class DescuentoEdad implements Descuento {

	//El booleano 'joven' indica si el DescuentoEdad se aplica por una condición u otra
	private boolean joven;
	public DescuentoEdad(boolean joven) {
		this.joven = joven;
	}
	
	@Override
	public double getPrecioPremium() {
		if (joven) return Descuento.PRECIO_PREMIUM * 0.75;
		return Descuento.PRECIO_PREMIUM * 0.85;
	}
}
