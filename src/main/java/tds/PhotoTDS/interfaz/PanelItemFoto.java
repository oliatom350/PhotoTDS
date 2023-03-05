package tds.PhotoTDS.interfaz;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tds.PhotoTDS.Foto;

public class PanelItemFoto extends JPanel {

private static final long serialVersionUID = 1L;

public PanelItemFoto (Foto f) {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JPanel panelFoto = new JPanel();
		this.add(panelFoto);
		JLabel foto = new JLabel("");
		foto.setIcon(new ImageIcon(VentanaPrincipal.class.getResource(f.getPath())));
		panelFoto.add(foto);
		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new BoxLayout(panelInfo,BoxLayout.Y_AXIS));
		this.add(panelInfo);
		//TODO Añadir info de la foto en panelInfo
	}
}
