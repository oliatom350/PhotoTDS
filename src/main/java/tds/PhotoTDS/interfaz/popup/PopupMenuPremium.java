package tds.PhotoTDS.interfaz.popup;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopupMenuPremium extends JPopupMenu {

	private static final long serialVersionUID = 3817779545038902831L;

	public PopupMenuPremium() {
		JMenuItem premium = new JMenuItem("Premium");
		this.add(premium);
		
		JMenuItem genPDF = new JMenuItem("Generar PDF");
		this.add(genPDF);
		
		JMenuItem genExcel = new JMenuItem("Generar Excel");
		this.add(genExcel);
		
		JMenuItem topMG = new JMenuItem("Top Me Gusta");
		this.add(topMG);
	}
}
