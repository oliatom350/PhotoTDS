package tds.PhotoTDS;

import java.util.ArrayList;

public class mainTestTomas {

	public static void main(String[] args) throws Exception {
		
//		ServicioPersistencia servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
//
//		for(Entidad e : servPersistencia.recuperarEntidades())
//			servPersistencia.borrarEntidad(e);
		/*Fotos fotos = MapperFotosXMLtoJava
				.cargarFotos("xml/fotos.xml");
				//Obtener fichero a cargar mediante JFileChooser en Swing
				for (umu.tds.fotos.Foto foto: fotos.getFoto()) {
				System.out.println("Titulo: " + foto.getTitulo());
				System.out.println(" Descripcion: " + foto.getDescripcion());
				System.out.println(" Path : " + foto.getPath());
				System.out.println(" HashTags : ");
				foto.getHashTags().stream()
				.flatMap(h -> h.getHashTag().stream())
				.forEach(h -> System.out.println(" " + h + " "));
				System.out.println("***** ***** *****");
				}*/
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		ArrayList<String> hashtags = new ArrayList<>();
		hashtags.add("#TOTY");
		/*Foto f = new Foto("TOTY", "Militao TOTY VAMOOOO", hashtags, "asdiia", "soyRuta");
		controlador.registrarFoto(f);
		System.out.println(controlador.getFoto(f.getId()).getPath());*/
	}
}
