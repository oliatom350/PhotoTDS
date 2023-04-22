package tds.PhotoTDS;

//Para crear un nuevo descuento futuro, basta con crear el fichero y que implemente esta interfaz
public interface Descuento {
	public double getPrecioPremium(Usuario usuario, double precioPremium);
}
