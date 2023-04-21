package tds.PhotoTDS.interfaz;

import java.awt.Image;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import tds.PhotoTDS.Foto;
import tds.PhotoTDS.PhotoTDS;
import tds.PhotoTDS.Usuario;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class PanelPerfil extends JPanel {

	/**
	 * Create the panel.
	 */
	private int ancho = 100;
	private int alto = 100;
	
	@SuppressWarnings("deprecation")
	public PanelPerfil(Usuario usuario, Usuario usuarioVP) {
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		
		//USUARIO PARA PRUEBAS
		usuario = new Usuario("jasf723", "jose@gmail.com", "JoseAntonio", new Date(2006, 10, 26), false, "1234", "SU.png", "Ofrezco: esquizofrenia, más guarro que la uña de un camionero, fimosis, virgen a los 40, en el paro, tengo la fp de mecánica");
		ArrayList<Foto> fotosPrueba = new ArrayList<Foto>();
		for(int i = 0; i < 1; i++) {
			fotosPrueba.add(new Foto("", "", new ArrayList<String>() , 0, "SU.png"));
			fotosPrueba.add(new Foto("", "", new ArrayList<String>() , 0, "SU.png"));
		}
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelInfoUsuario = new JPanel();
		panelInfoUsuario.setMaximumSize(new Dimension(500, 150));
		panelInfoUsuario.setMinimumSize(new Dimension(500, 150));
		add(panelInfoUsuario);
		panelInfoUsuario.setLayout(new BorderLayout(0, 0));
		
		JPanel panelFotoPerfil = new JPanel();
		panelInfoUsuario.add(panelFotoPerfil, BorderLayout.WEST);
		
		JLabel fotoLabel = new JLabel("");
		Image foto = new ImageIcon(PanelPerfil.class.getResource("/images/SU.png")).getImage();
		panelFotoPerfil.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		fotoLabel.setIcon(new ImageIcon(foto.getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH)));
		//fotoLabel.setIcon(new ImageIcon(System.getProperty("user.dir")+PhotoTDS.pathFotos+usuario.getFotoPerfil()));
		panelFotoPerfil.add(fotoLabel);
		
		JPanel panelInfo = new JPanel();
		panelInfoUsuario.add(panelInfo, BorderLayout.CENTER);
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
		
		JPanel panelNombreUsuario = new JPanel();
		FlowLayout fl_panelNombreUsuario = (FlowLayout) panelNombreUsuario.getLayout();
		fl_panelNombreUsuario.setHgap(20);
		fl_panelNombreUsuario.setAlignment(FlowLayout.LEADING);
		panelInfo.add(panelNombreUsuario);
		
		JLabel nombreUsuario = new JLabel(usuario.getNombre());
		panelNombreUsuario.add(nombreUsuario);
		
		JPanel panelInfoPubli = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelInfoPubli.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		flowLayout_1.setHgap(20);
		panelInfo.add(panelInfoPubli);
		
		//Calcula la información necesaria
		int nPublicaciones = 0;
		int nSeguidores = 0;
		int nSeguidos = 0;
		try {
			//nPublicaciones = controlador.getAlbumesUsuario(usuario.getId()).size() + controlador.getFotosUsuario(usuario.getId()).size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nSeguidores = usuario.getUsuariosSeguidores().size();
		nSeguidos = usuario.getUsuariosSeguidos().size();
		
		//JLabel publicLabel = new JLabel(Integer.toString(nPublicaciones) + " publicaciones");
		JLabel publicLabel = new JLabel("n seguidores");
		panelInfoPubli.add(publicLabel);
		
		JLabel seguidoresLabel = new JLabel(Integer.toString(nSeguidores) + " seguidores");
		panelInfoPubli.add(seguidoresLabel);
		
		JLabel seguidosLabel = new JLabel(Integer.toString(nSeguidos) + " seguidos");
		panelInfoPubli.add(seguidosLabel);
		
		JPanel panelNombre = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelNombre.getLayout();
		flowLayout.setHgap(20);
		flowLayout.setAlignment(FlowLayout.LEADING);
		panelInfo.add(panelNombre);
		
		JLabel nombreCompletoLabel = new JLabel(usuario.getNombreCompleto());
		panelNombre.add(nombreCompletoLabel);
		
		JPanel panelSeleccion = new JPanel();
		add(panelSeleccion);
		
		JButton botonFotos = new JButton("New button");
		panelSeleccion.add(botonFotos);
		
		JButton botonAlbumes = new JButton("New button");
		panelSeleccion.add(botonAlbumes);
		
		JScrollPane panel_1 = new JScrollPane();
		panel_1.setBackground(Color.WHITE);
		add(panel_1);
		JList<Foto> listafotos = new JList<Foto>();
		DefaultListModel<Foto> fotoslistModel = new DefaultListModel<Foto>();
		listafotos.setModel(fotoslistModel);
		try {
			//controlador.getFotosUsuario(usuario.getId()).forEach(f -> fotoslistModel.addElement(f));
			fotosPrueba.stream().forEach(f -> fotoslistModel.addElement(f));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listafotos.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listafotos.setVisibleRowCount(-1);
		listafotos.ensureIndexIsVisible(getHeight());
		listafotos.setCellRenderer(createListRenderer());
		panel_1.setViewportView(listafotos);
		//CREACION MATRIZ DE FOTOS
		
	}
	
	private static ListCellRenderer<? super Foto> createListRenderer() {
		return new DefaultListCellRenderer() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private Color background = new Color(0, 100, 255, 15);
			private Color defaultBackground = (Color) UIManager.get("List.background");
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value,
														int index, boolean isSelected,
														boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (c instanceof JLabel) {
					JLabel label = (JLabel) c;
					Foto foto = (Foto) value;
					label.setText("");
					label.setIcon(new ImageIcon((new ImageIcon(System.getProperty("user.dir")+PhotoTDS.pathFotos+foto.getPath()).getImage().getScaledInstance(70, 40, java.awt.Image.SCALE_SMOOTH))));
				}
				return c;
			}
		 }; 
	}


}


