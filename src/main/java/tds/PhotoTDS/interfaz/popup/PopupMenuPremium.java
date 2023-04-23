package tds.PhotoTDS.interfaz.popup;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import tds.PhotoTDS.GeneradorExcel;
import tds.PhotoTDS.GeneradorPDF;
import tds.PhotoTDS.Usuario;
import tds.PhotoTDS.interfaz.VentanaDescuentosAplicables;
import tds.PhotoTDS.interfaz.VentanaTopMeGusta;
import tds.PhotoTDS.interfaz.VentanaWarning;

@SuppressWarnings("serial")
public class PopupMenuPremium extends JPopupMenu {

	public PopupMenuPremium(Usuario user) {
		//Botón para hacerse premium en caso de no serlo ya
		JMenuItem premium = new JMenuItem("Premium");
		premium.addActionListener(ev -> {
			VentanaDescuentosAplicables vda = new VentanaDescuentosAplicables(user);
			if (vda.existenDescuentos()) {
				vda.setVisible(true);
			}
		});
		this.add(premium);
		
		// Generar un archivo Excel con la lista de sus seguidores: filas con 3 columnas para registrar su nombre, email, y presentación;
		JMenuItem genExcel = new JMenuItem("Generar Excel");
		genExcel.addActionListener(ev -> {
			if (warningNoPremium(user)) {
				new GeneradorExcel(user);
			}
		});
		this.add(genExcel);
				
		// Generar un archivo PDF con la misma información que el Excel pero con una tabla de 3 columnas
		JMenuItem genPDF = new JMenuItem("Generar PDF");
		genPDF.addActionListener(ev -> {
			if (warningNoPremium(user)) {
				new GeneradorPDF(user);
			}
		});
		this.add(genPDF);
		
		//Visualizar la lista de las 10 fotos del usuario que más “me gusta” han recibido
		JMenuItem topMG = new JMenuItem("Top Me Gusta");
		topMG.addActionListener(ev -> {
			if (warningNoPremium(user)) {
				VentanaTopMeGusta vtmg = new VentanaTopMeGusta();
				vtmg.setVisible(true);
			}
		});
		this.add(topMG);
	}
	
	public boolean warningNoPremium(Usuario user) {
		if (!user.isPremium()) {
			VentanaWarning vw = new VentanaWarning("¡Usuario no premium!");
			vw.setVisible(true);
			//No permitimos al usuario acceder a esta función
			return false;
		}
		//El usuario si tiene permiso de acceso
		return true;
	}
}
