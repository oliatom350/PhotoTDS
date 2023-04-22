package tds.PhotoTDS.interfaz.popup;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import tds.PhotoTDS.Foto;
import tds.PhotoTDS.PhotoTDS;

public class PopupMenuFoto extends JPopupMenu {
	
	private static final long serialVersionUID = -1213976366962644102L;

	public PopupMenuFoto(Foto foto) {
		JMenuItem eliminar = new JMenuItem("Eliminar");
		eliminar.addActionListener(ev -> {
			PhotoTDS.getUnicaInstancia().eliminarFoto(foto);
		});
		this.add(eliminar);
	}
}
