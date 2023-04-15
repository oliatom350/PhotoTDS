package tds.PhotoTDS.interfaz.popup;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopupMenuFoto extends JPopupMenu {

	private static final long serialVersionUID = -1213976366962644102L;

	public PopupMenuFoto() {
		JMenuItem eliminar = new JMenuItem("Eliminar");
		this.add(eliminar);
	}
}
