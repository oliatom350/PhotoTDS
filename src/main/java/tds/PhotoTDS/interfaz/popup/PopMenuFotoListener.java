package tds.PhotoTDS.interfaz.popup;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import tds.PhotoTDS.Foto;

public class PopMenuFotoListener extends MouseAdapter {
	private Foto foto; 
	
	public PopMenuFotoListener(Foto foto) {
		super();
		this.foto = foto;
	}
	
	public void mousePressed(MouseEvent e) {
		 if (e.isPopupTrigger())
			 mostrarMenu(e);
	 }
	 public void mouseReleased(MouseEvent e) {
		 if (e.isPopupTrigger())
			 mostrarMenu(e);
	 }
	 private void mostrarMenu(MouseEvent e) {
		 PopupMenuFoto menu = new PopupMenuFoto(foto);
		 menu.show(e.getComponent(), e.getX(), e.getY());
	 }
}
