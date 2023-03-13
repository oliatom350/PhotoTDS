package tds.PhotoTDS.interfaz;

import java.awt.Image;

import javax.swing.*;

import tds.PhotoTDS.Foto;

public class PanelItemFoto extends JPanel {

private static final long serialVersionUID = 1L;
private static final int width = 150;
private static final int height = 100;

public PanelItemFoto (Foto f) {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JPanel panelFoto = new JPanel();
		this.add(panelFoto);
		JLabel foto = new JLabel("");
		//TODO Problema de no visualizacion de fotos con alta resolucion
		Image icon = new ImageIcon(VentanaPrincipal.class.getResource(f.getPath())).getImage();
		foto.setIcon(new ImageIcon(icon.getScaledInstance(width, height, ABORT)));
		panelFoto.add(foto);
		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new BoxLayout(panelInfo,BoxLayout.Y_AXIS));
		this.add(panelInfo);
		//TODO Ajustar posicion info de la foto en panelInfo
		String mg = Integer.toString(f.getMeGusta());
		JLabel mgLabel = new JLabel(mg + " me gusta");
		JButton mgButton = new JButton("MG");
		JButton commentButton = new JButton("comenta");
		//TODO Añadir icono corazon al boton mg y el de comentario al de comment
		panelInfo.add(mgLabel);
		JPanel panelBotonInfo = new JPanel();
		panelBotonInfo.add(mgButton);
		panelBotonInfo.add(commentButton);
		panelInfo.add(panelBotonInfo);
	}
}
