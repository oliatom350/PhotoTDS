package tds.PhotoTDS;

public class Comentario {

	//Atributos
	private int id;
	private String texto;
	private int usuario;
	
	
	//Constructor
	public Comentario(String texto, int usuario) {
		this.texto = texto;
		this.usuario = usuario;
	}
	
	//Metodos
	public String getTexto() {
		return texto;
	}
	
	public int getUsuario() {
		return usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
