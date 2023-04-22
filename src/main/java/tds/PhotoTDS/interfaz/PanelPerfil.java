package tds.PhotoTDS.interfaz;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

import tds.PhotoTDS.Foto;
import tds.PhotoTDS.PhotoTDS;
import tds.PhotoTDS.Usuario;
import tds.PhotoTDS.interfaz.popup.PopMenuFotoListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PanelPerfil extends JPanel {

	private int ancho = 100;
	private int alto = 100;
	private JList<Foto> listafotos;
	
	public PanelPerfil(Usuario usuario, Usuario usuarioVP) {
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		
		//USUARIO PARA PRUEBAS
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelInfoUsuario = new JPanel();
		panelInfoUsuario.setMaximumSize(new Dimension(500, 100));
		panelInfoUsuario.setMinimumSize(new Dimension(500, 100));
		panelInfoUsuario.setPreferredSize(new Dimension(500, 100));
		add(panelInfoUsuario);
		panelInfoUsuario.setLayout(new BorderLayout(0, 0));
		
		JPanel panelFotoPerfil = new JPanel();
		panelInfoUsuario.add(panelFotoPerfil, BorderLayout.WEST);
		
		JLabel fotoLabel = new JLabel("");
		String path = usuario.getPathFotoPerfil();
		Image foto = new ImageIcon(path).getImage();
		panelFotoPerfil.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		fotoLabel.setIcon(new ImageIcon(foto.getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH)));
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
			e.printStackTrace();
		}
		nSeguidores = usuario.getUsuariosSeguidores().size();
		nSeguidos = usuario.getUsuariosSeguidos().size();
		
		JLabel publicLabel = new JLabel(Integer.toString(nPublicaciones) + " publicaciones");
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
		
		JButton botonFotos = new JButton("Fotos");
		panelSeleccion.add(botonFotos);
		
		JButton botonAlbumes = new JButton("Albumes");
		panelSeleccion.add(botonAlbumes);
		
		JScrollPane panel_1 = new JScrollPane();
		panel_1.setBackground(Color.WHITE);
		add(panel_1);
		listafotos = new JList<Foto>();
		DefaultListModel<Foto> fotoslistModel = new DefaultListModel<Foto>();
		listafotos.setModel(fotoslistModel);
		try {
			controlador.getFotosUsuario(usuario.getId()).forEach(f -> fotoslistModel.addElement(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		listafotos.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listafotos.setVisibleRowCount(-1);
		listafotos.ensureIndexIsVisible(getHeight());
		listafotos.setCellRenderer(createListRenderer());
		listafotos.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	if (SwingUtilities.isRightMouseButton(e)) {
                    int index = listafotos.locationToIndex(e.getPoint());
                    if (index != -1 && listafotos.getCellBounds(index, index).contains(e.getPoint())) {
                        listafotos.setSelectedIndex(index);
                        mostrarMenuEmergente(e);
                    }
                }
            }
        });
		panel_1.setViewportView(listafotos);
		//CREACION MATRIZ DE FOTOS
		
		//Creación botones seguir o editar perfil
		JButton botonEditarPerfil = new JButton("Editar Perfil");
		botonEditarPerfil.setBackground(new Color(0, 128, 192));
		botonEditarPerfil.setOpaque(true);
		botonEditarPerfil.setBorderPainted(false);
		botonEditarPerfil.addActionListener(e -> {
			//Insertar Ventana para editar perfil
			VentanaRegistro vR = new VentanaRegistro(usuarioVP);
			vR.setVisible(true);
		});
		
		JButton botonSeguir = new JButton("Seguir");
		botonSeguir.setBackground(new Color(0, 128, 192));
		botonSeguir.setOpaque(true);
		botonSeguir.setBorderPainted(false);
		botonSeguir.addActionListener(e -> {
			if (usuario.getUsuariosSeguidores().contains(usuarioVP.getId())) {
				usuario.addSeguidor(usuarioVP.getId());
				usuarioVP.addSeguido(usuario.getId());
				controlador.modificarUsuario(usuario);
				controlador.modificarUsuario(usuarioVP);
			}
		});
		
		
		if(usuario.equals(usuarioVP)) {
			panelNombreUsuario.add(botonEditarPerfil);
		} else
			panelNombreUsuario.add(botonSeguir);
		
	}
	
	private void mostrarMenuEmergente(MouseEvent e) {
		JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem eliminarItem = new JMenuItem("Eliminar");
        eliminarItem.addActionListener((ActionEvent event) -> {
            Foto foto = listafotos.getSelectedValue();
            PhotoTDS.getUnicaInstancia().eliminarFoto(foto);
            this.revalidate();
            this.repaint();
            this.validate();
        });
        popupMenu.add(eliminarItem);
        popupMenu.show(e.getComponent(), e.getX(), e.getY());
	}
	
	private static ListCellRenderer<? super Foto> createListRenderer() {
		return new DefaultListCellRenderer() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value,
														int index, boolean isSelected,
														boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (c instanceof JLabel) {
					JLabel label = (JLabel) c;
					Foto foto = (Foto) value;
					label.setText("");
					label.setIcon(new ImageIcon((new ImageIcon(foto.getPath()).getImage().getScaledInstance(90, 60, java.awt.Image.SCALE_SMOOTH))));
				}
				return c;
			}
		 }; 
	}


}


