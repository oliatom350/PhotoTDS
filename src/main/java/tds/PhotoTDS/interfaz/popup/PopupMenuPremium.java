package tds.PhotoTDS.interfaz.popup;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import tds.PhotoTDS.GeneradorExcel;
import tds.PhotoTDS.Usuario;
import tds.PhotoTDS.interfaz.VentanaDescuentosAplicables;

public class PopupMenuPremium extends JPopupMenu {

	private static final long serialVersionUID = 3817779545038902831L;

	public PopupMenuPremium(Usuario user) {
		//Botón para hacerse premium en caso de no serlo ya
		JMenuItem premium = new JMenuItem("Premium");
		premium.addActionListener(ev -> {
			VentanaDescuentosAplicables vda = new VentanaDescuentosAplicables(user);
			vda.setVisible(true);
		});
		this.add(premium);
		
		// Generar un archivo Excel con la lista de sus seguidores: filas con 3 columnas para registrar su nombre, email, y presentación;
		JMenuItem genExcel = new JMenuItem("Generar Excel");
		genExcel.addActionListener(ev -> {
			new GeneradorExcel();
		});
		this.add(genExcel);
				
		// Generar un archivo PDF con la misma información que el Excel pero con una tabla de 3 columnas
		JMenuItem genPDF = new JMenuItem("Generar PDF");
		this.add(genPDF);
		
		//Visualizar la lista de las 10 fotos del usuario que más “me gusta” han recibido
		JMenuItem topMG = new JMenuItem("Top Me Gusta");
		this.add(topMG);
	}
	
	
}
