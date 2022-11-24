package tds.PhotoTDS;

public class Comentario {

	//Atributos
	private int id;
	private String texto;
	private String usuario;
	
	
	//Constructor
	public Comentario(String texto, String usuario) {
		this.texto = texto;
		this.usuario = usuario;
	}
	
	//Metodos
	public String getTexto() {
		return texto;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public int getId() {
		return id;
	}
	
}
