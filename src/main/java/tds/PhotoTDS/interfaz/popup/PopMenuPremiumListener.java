package tds.PhotoTDS.interfaz.popup;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import tds.PhotoTDS.Usuario;

public class PopMenuPremiumListener extends MouseAdapter {
	 public void mousePressed(MouseEvent e) {
		 if (e.isPopupTrigger())
			 mostrarMenu(e, null);
	 }
	 public void mouseReleased(MouseEvent e) {
		 if (e.isPopupTrigger())
			 mostrarMenu(e, null);
	 }
	 private void mostrarMenu(MouseEvent e, Usuario user) {
		 PopupMenuPremium menu = new PopupMenuPremium(user);
		 menu.show(e.getComponent(), e.getX(), e.getY());
	 }
}
