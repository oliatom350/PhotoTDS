package tds.PhotoTDS;

//Para crear un nuevo descuento futuro, basta con crear el fichero y que implemente esta interfaz
public interface Descuento {
	public static final double PRECIO_PREMIUM = 10;
	
	public double getPrecioPremium();
}
