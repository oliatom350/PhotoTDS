package tds.PhotoTDS.interfaz;

import java.awt.Image;

import javax.swing.*;

import tds.PhotoTDS.Foto;
import tds.PhotoTDS.PhotoTDS;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class PanelItemFoto extends JPanel {

	private static final long serialVersionUID = -6672653773330799809L;
	
	private static final int width = 200;
	private static final int height = 150;
	
	public PanelItemFoto (Foto f, int idUsuario) {
		setBorder(UIManager.getBorder("CheckBox.border"));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JPanel panelFoto = new JPanel();
		panelFoto.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 128, 128), null, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		this.add(panelFoto);
		JLabel fotoLabel = new JLabel("");
		//TODO Problema de no visualizacion de fotos con alta resolucion
		Image icon = new ImageIcon(f.getPath()).getImage();
		panelFoto.setLayout(new BoxLayout(panelFoto, BoxLayout.X_AXIS));
		fotoLabel.setIcon(new ImageIcon(icon.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		panelFoto.add(fotoLabel);
		JPanel panelInfo = new JPanel();
		this.add(panelInfo);
		
		String mg = Integer.toString(f.getMeGusta());
		JLabel mgLabel = new JLabel("   " + mg + " me gusta");
		
		JButton mgButton = new JButton("");
		icon = new ImageIcon(PanelItemFoto.class.getResource("/images/me-gusta.png")).getImage();
		mgButton.setIcon(new ImageIcon(icon.getScaledInstance(15, 15, Image.SCALE_DEFAULT)));
		mgButton.addActionListener(ev -> {
			PhotoTDS.getUnicaInstancia().addMeGusta(f);
			String newMg = Integer.toString(f.getMeGusta());
			mgLabel.setText("   " + newMg + " me gusta");
		});
		
		JButton commentButton = new JButton("");
		icon = new ImageIcon(PanelItemFoto.class.getResource("/images/comment.png")).getImage();
		panelInfo.setLayout(new BorderLayout(0, 0));
		commentButton.setIcon(new ImageIcon(icon.getScaledInstance(15, 15, Image.SCALE_DEFAULT)));
		commentButton.addActionListener(ev -> {
			addComentario(idUsuario, f);
		});
		
		JPanel panelBotonInfo = new JPanel();
		panelBotonInfo.setLayout(new BoxLayout(panelBotonInfo, BoxLayout.X_AXIS));
		JLabel aux = new JLabel("  ");
		panelBotonInfo.add(mgButton);
		panelBotonInfo.add(aux);
		panelBotonInfo.add(commentButton);
		panelBotonInfo.add(mgLabel);
		
		JPanel panelInfoUsuario = new JPanel();
		panelInfoUsuario.setLayout(new BoxLayout(panelInfoUsuario, BoxLayout.X_AXIS));
		JLabel fotoUsuario = new JLabel();
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		try {
			icon = new ImageIcon(controlador.getFotoUsuario(f.getUsuario())).getImage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		fotoUsuario.setIcon(new ImageIcon(icon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		JLabel usuario = new JLabel("   " + f.getNickUsuario(f.getUsuario()));
		
		panelInfoUsuario.add(fotoUsuario);
		panelInfoUsuario.add(usuario);
		
		JPanel panelIntermedio = new JPanel();
		panelIntermedio.setLayout(new GridLayout(2, 1));
		panelIntermedio.add(panelBotonInfo);
		panelIntermedio.add(panelInfoUsuario);
		panelInfo.add(panelIntermedio);
		
		JPanel espacio = new JPanel();
		panelInfo.add(espacio, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		panelInfo.add(panel, BorderLayout.NORTH);
	}
	
	public void addComentario(int idUsuario, Foto f) {
		VentanaAddComentario vac = new VentanaAddComentario(idUsuario, f);
		vac.setVisible(true);
//		VentanaVerFoto vvf = new VentanaVerFoto(f);
//		vvf.setVisible(true);
	}
}
