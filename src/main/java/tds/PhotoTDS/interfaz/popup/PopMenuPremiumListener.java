package tds.PhotoTDS.interfaz.popup;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopMenuPremiumListener extends MouseAdapter {
	 public void mousePressed(MouseEvent e) {
		 if (e.isPopupTrigger())
			 mostrarMenu(e);
	 }
	 public void mouseReleased(MouseEvent e) {
		 if (e.isPopupTrigger())
			 mostrarMenu(e);
	 }
	 private void mostrarMenu(MouseEvent e) {
		 PopupMenuPremium menu = new PopupMenuPremium();
		 menu.show(e.getComponent(), e.getX(), e.getY());
	 }
}
