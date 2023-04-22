package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tds.PhotoTDS.Foto;
import tds.PhotoTDS.PhotoTDS;
import tds.PhotoTDS.Usuario;
import tds.PhotoTDS.interfaz.popup.PopupMenuPremium;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import pulsador.Luz;

public class VentanaPrincipal extends JFrame implements Observer {

	private static final long serialVersionUID = -2940916222961478615L;
	
	private JPanel contentPane;
	private JButton searchButton;
	private Luz xmlLoaderButton;
	private JPanel panelNorte;
	private JPanel panelNorteCentral;
	private JPanel panelNorteOeste;
	private JPanel panelNorteEste;
	private JPanel panelCentralCentro;
	private JPanel panelCentral;
	private CardLayout cardLayout;
	private JScrollPane scrollPane;
	@SuppressWarnings("unused")
	private boolean mostrarPerfil = false;
	private static Usuario usuarioAct;
	
	private VentanaBusquedaUsuario vBU;
	
	//Array de fotos a mostrar en la ventana principal
	ArrayList<Foto> fotos = new ArrayList<Foto>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal(usuarioAct);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaPrincipal(Usuario user) {
		
		int idUsuarioAct = user.getId();
		usuarioAct = user;
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		try {
			fotos = controlador.getFotosSeguidos(idUsuarioAct);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 550, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));
		
		panelNorteCentral = new JPanel();
		panelNorte.add(panelNorteCentral, BorderLayout.CENTER);
		
		panelNorteOeste = new JPanel();
		panelNorte.add(panelNorteOeste, BorderLayout.WEST);
		
		panelNorteEste = new JPanel();
		JLabel fotoPerfil = new JLabel();
		Image icon = new ImageIcon().getImage();
		try {
			String path = controlador.getUsuario(idUsuarioAct).getPathFotoPerfil();
			icon = new ImageIcon(path).getImage();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		fotoPerfil.setIcon(new ImageIcon(icon.getScaledInstance(30, 30, DO_NOTHING_ON_CLOSE)));
		fotoPerfil.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				update(user);
			}
		});
		panelNorteEste.add(fotoPerfil);
		JButton barrasMenuPremium = new JButton();
		icon = new ImageIcon(VentanaPrincipal.class.getResource("/images/premium.png")).getImage();
		barrasMenuPremium.setIcon(new ImageIcon(icon.getScaledInstance(30, 30, DO_NOTHING_ON_CLOSE)));
		barrasMenuPremium.addActionListener(ev -> {
			PopupMenuPremium menu = new PopupMenuPremium(usuarioAct);
			menu.show(barrasMenuPremium, 0, barrasMenuPremium.getHeight());
		});
		panelNorteEste.add(barrasMenuPremium);
		panelNorte.add(panelNorteEste, BorderLayout.EAST);
		
		JButton addFoto = new JButton("");
		Image iconAddFoto = new ImageIcon(VentanaPrincipal.class.getResource("/images/iconoplus.png")).getImage();
		addFoto.setIcon(new ImageIcon(iconAddFoto.getScaledInstance(20, 20, DO_NOTHING_ON_CLOSE)));
		addFoto.addActionListener(ev -> {
			this.setVisible(false);
			nuevaFoto();
		});
		panelNorteCentral.add(addFoto);
		
		JTextField textField = new JTextField();
		textField.setColumns(10);
		panelNorteCentral.add(textField);
		
		searchButton = new JButton("");
		iconAddFoto = new ImageIcon(VentanaPrincipal.class.getResource("/images/iconolupa.png")).getImage();
		searchButton.setIcon(new ImageIcon(iconAddFoto.getScaledInstance(15, 15, DO_NOTHING_ON_CLOSE)));
		searchButton.addActionListener(ev -> {
			abreBusqueda(textField.getText());
		});
		panelNorteCentral.add(searchButton);
		
		xmlLoaderButton = new Luz();
		panelNorteCentral.add(xmlLoaderButton);
		
		panelCentral = new JPanel();
		scrollPane = new JScrollPane(panelCentral);
		//panelCentral.setLayout(new BorderLayout(0, 0));
		cardLayout = new CardLayout();
		panelCentral.setLayout(cardLayout);
		contentPane.add(scrollPane,BorderLayout.CENTER);
		
		panelCentralCentro = new JPanel();
		panelCentral.add(panelCentralCentro, BorderLayout.CENTER);
		GridBagLayout gbl = new GridBagLayout();
		panelCentralCentro.setLayout(gbl);
		int cont = 0;
		
		for (Foto foto : fotos) {
			PanelItemFoto pif = new PanelItemFoto(foto, idUsuarioAct);
			pif.setMaximumSize(new Dimension(523, 150));
			pif.setMinimumSize(new Dimension(523, 150));
			pif.setPreferredSize(new Dimension(523, 150));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.weighty = 1;
			gbc.gridy = cont;
			cont++;
			panelCentralCentro.add(pif, gbc);
		}
		
		//Nuevo boton de inicio
		JButton inicioButton = new JButton("Inicio");
		inicioButton.addActionListener(ev -> {
			if (panelCentral.getComponent(0) != panelCentralCentro) {
				panelCentral.add(panelCentralCentro, BorderLayout.CENTER);
				cardLayout.next(panelCentral);
				panelCentral.remove(0);
			}
		});
		panelNorteCentral.add(inicioButton);
		
	}
	
	public void nuevaFoto() {
		VentanaAddFoto vaf = new VentanaAddFoto(usuarioAct.getId());
		vaf.setVisible(true);
		//TODO Al cerrar la VentanaAddFoto se cierra también VentanaPrincipal
	}
	
	public void abreBusqueda(String entrada) {
		// Abre ventana de busqueda en funcion de si el primer caracter un # o no
		String primerCaracter = entrada.substring(0, 1);
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		if (primerCaracter.equals("#")) {
			try {
				VentanaBusquedaHashtag vbh = new VentanaBusquedaHashtag(controlador.getHashtagsBusqueda(entrada.substring(1)));
				vbh.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				vBU = new VentanaBusquedaUsuario(controlador.getUsuariosBusqueda(entrada));
				vBU.addObserver(this);
				vBU.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void update(Usuario usuario) {
		mostrarPerfil = true;
		PanelPerfil panel_dos = new PanelPerfil(usuario, usuarioAct);
		panelCentral.add(panel_dos, BorderLayout.CENTER);
		cardLayout.next(panelCentral);
		panelCentral.remove(0);
	}

}
