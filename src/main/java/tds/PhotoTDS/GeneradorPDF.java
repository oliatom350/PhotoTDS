package tds.PhotoTDS;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import tds.PhotoTDS.interfaz.VentanaWarning;

public class GeneradorPDF {
	
	public GeneradorPDF(Usuario user) {
		PdfWriter writer = null;
		try {
			writer = new PdfWriter("src\\test\\java\\tds\\PhotoTDS\\"+ user.getNombre()+".pdf");
		} catch (FileNotFoundException e1) {
			VentanaWarning vw = new VentanaWarning("PDF no creado, ruta errónea");
			vw.setVisible(true);
		}
		PdfDocument documentoPDF = new PdfDocument(writer);
		Document documento = new Document(documentoPDF);
		
		//Creamos tabla de 3 columnas
		Table tabla = new Table(3);
		tabla.addCell("Nombre");
		tabla.addCell("Email");
		tabla.addCell("Presentación");
		
		ArrayList<Integer> seguidores = user.getUsuariosSeguidores();
        for (int i = 0; i < seguidores.size(); ++i) {
            Usuario u;
			try {
				u = PhotoTDS.getUnicaInstancia().getUsuario(seguidores.get(i));
				tabla.addCell(u.getNombreCompleto());
				tabla.addCell(u.getEmail());
				tabla.addCell(u.getPresentacion());
			} catch (Exception e) {
				System.out.println("Usuario " + i + " no encontrado");
			}
        }
		
        documento.add(tabla);
        documento.close();
		VentanaWarning vw = new VentanaWarning("PDF creado exitosamente");
		vw.setVisible(true);
	}
}
