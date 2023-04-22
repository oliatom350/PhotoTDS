package tds.PhotoTDS.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import tds.PhotoTDS.Album;
import tds.PhotoTDS.Foto;
import tds.PhotoTDS.PhotoTDS;
import tds.PhotoTDS.Usuario;

public class VentanaEditarAlbum extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Album album;
	private static int usuario;
	private JList<Foto> listafotos;
	private JScrollPane panelFotos;
	private static int anchof = 110;
	private static int altof = 80;
	private static boolean visitante = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditarAlbum frame = new VentanaEditarAlbum(usuario, album, visitante);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaEditarAlbum(int usuario, Album album, boolean visitante) {
		VentanaEditarAlbum.album = album;
		VentanaEditarAlbum.visitante = visitante;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);
		
		//Boton
		JPanel panelBoton = new JPanel();
		panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(panelBoton);
		
		
		JButton addFotoBoton = new JButton("Añade una foto");
		addFotoBoton.addActionListener(ev -> {
			VentanaAddFoto vAF = new VentanaAddFoto(usuario, album.getTitulo());
			vAF.setVisible(true);
		});
		
		JButton addBotonAct = new JButton("Actualiza el contenido");
		addBotonAct.addActionListener(ev -> {
			cargarMatrizFotos();
		});
		
		if(!visitante) {
			panelBoton.add(addFotoBoton);
			panelBoton.add(addBotonAct);
		}
		
		panelBoton.setMaximumSize(new Dimension(450, 50));
		panelBoton.setMinimumSize(new Dimension(450, 50));
		panelBoton.setPreferredSize(new Dimension(450, 50));
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panelFotos = new JScrollPane();
		panelFotos.setBackground(Color.WHITE);
		contentPane.add(panelFotos);
		
		cargarMatrizFotos();
		
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
					label.setIcon(new ImageIcon((new ImageIcon(foto.getPath()).getImage().getScaledInstance(anchof, altof, java.awt.Image.SCALE_SMOOTH))));
				}
				return c;
			}
		 }; 
	}
	
	private void mostrarMenuEmergente(MouseEvent e) {
		JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem eliminarItem = new JMenuItem("Eliminar");
        eliminarItem.addActionListener((ActionEvent event) -> {
            Foto foto = listafotos.getSelectedValue();
            album.removeFoto(foto);
            cargarMatrizFotos();
        });
        if(!visitante)
        	popupMenu.add(eliminarItem);
        popupMenu.show(e.getComponent(), e.getX(), e.getY());
	}
	
	private void cargarMatrizFotos(){
		listafotos = new JList<Foto>();
		DefaultListModel<Foto> fotoslistModel = new DefaultListModel<Foto>();
		listafotos.setModel(fotoslistModel);
		album.getFotos().stream().forEach(f -> fotoslistModel.addElement(f));
		
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
		panelFotos.setViewportView(listafotos);
	}
	
}
