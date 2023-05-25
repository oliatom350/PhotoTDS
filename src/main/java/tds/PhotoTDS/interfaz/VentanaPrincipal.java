package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

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
import javax.swing.JFileChooser;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.EventObject;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;

import pulsador.IEncendidoListener;
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
	private JPanel panelCentralCard;
	private CardLayout cardLayout;
	private JScrollPane scrollPane;
	@SuppressWarnings("unused")
	private boolean mostrarPerfil = false;
	private static Usuario usuarioAct;
	private JButton addAlbum; 
	
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
		
		//Nuevo boton de notificaciones
		JButton notisButton = new JButton("");
		notisButton.setMaximumSize(new Dimension(20,20));
		notisButton.setMinimumSize(new Dimension(20,20));
		notisButton.setPreferredSize(new Dimension(20,20));
		notisButton.setBackground(new Color(255, 255, 255));
		Image notis = new ImageIcon(System.getProperty("user.dir")+PhotoTDS.pathFotos+"campanas.png").getImage();
		notisButton.setIcon(new ImageIcon(notis.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		notisButton.addActionListener(ev -> {
			VentanaNotificaciones vN = new VentanaNotificaciones(user);
			vN.setVisible(true);
		});
		
		panelNorteCentral.add(notisButton);
		
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
		barrasMenuPremium.setIcon(new ImageIcon(icon.getScaledInstance(20, 20, DO_NOTHING_ON_CLOSE)));
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
		xmlLoaderButton.addEncendidoListener(new IEncendidoListener() {
			@Override
			public void enteradoCambioEncendido(EventObject arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(getContentPane());
				File xmlFile = chooser.getSelectedFile();
				controlador.cargarFotosPath(xmlFile.getPath());
				VentanaXMLFotos vxml = new VentanaXMLFotos(controlador.getFotosCargador());
				vxml.setVisible(true);
				//xmlLoaderButton.setEncendido(false);
			}
		});
		panelNorteCentral.add(xmlLoaderButton);
		
		cardLayout = new CardLayout();
		panelCentralCard = new JPanel();
		panelCentralCard.setLayout(cardLayout);
		
		panelCentral = new JPanel();
		//panelCentral.setLayout(new BorderLayout(0, 0));
		panelCentral.setLayout(cardLayout);
		//panelCentralCard.add(panelCentral);
		
		scrollPane = new JScrollPane(panelCentral);
	
		contentPane.add(scrollPane,BorderLayout.CENTER);
		
		
		construirPanelFotos();
		
		//Nuevo boton de inicio
		JButton inicioButton = new JButton("Inicio");
		panelNorteCentral.add(inicioButton);
		
		//Nuevo boton para crear album en el perfil del usuario
		addAlbum = new JButton("A +");
		addAlbum.addActionListener(ev -> {
			VentanaAddAlbum vAA = new VentanaAddAlbum(usuarioAct.getId());
			vAA.setVisible(true);
			this.dispose();
		});
		
		inicioButton.addActionListener(ev -> {
			construirPanelFotos();
			if (panelCentral.getComponent(0) != panelCentralCentro) {
				panelCentral.add(panelCentralCentro, BorderLayout.CENTER);
				cardLayout.next(panelCentral);
				panelCentral.remove(0);
				panelNorteCentral.remove(addAlbum);
				panelNorteCentral.revalidate();
				panelNorteCentral.repaint();
			}
		});
		
	}
	
	public void construirPanelFotos() {
		try {
			fotos = PhotoTDS.getUnicaInstancia().getFotosSeguidos(usuarioAct.getId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		panelCentralCentro = new JPanel();
		panelCentral.add(panelCentralCentro, BorderLayout.CENTER);
		//GridBagLayout gbl = new GridBagLayout();
		//panelCentralCentro.setLayout(gbl);
		panelCentralCentro.setLayout(new BoxLayout(panelCentralCentro, BoxLayout.Y_AXIS));
		int cont = 0;
		
		for (Foto foto : fotos) {
			PanelItemFoto pif = new PanelItemFoto(foto, usuarioAct.getId());
			pif.setMaximumSize(new Dimension(523, 150));
			pif.setMinimumSize(new Dimension(523, 150));
			pif.setPreferredSize(new Dimension(523, 150));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.weighty = 1;
			gbc.insets = new Insets(0,0,0,0);
			gbc.gridy = cont;
			cont++;
			panelCentralCentro.add(pif, gbc);
		}
	}
	
	
	public void nuevaFoto() {
		VentanaAddFoto vaf = new VentanaAddFoto(usuarioAct.getId());
		vaf.setVisible(true);
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
		if (usuario.equals(usuarioAct)) {
			mostrarPerfil = true;
			panelNorteCentral.add(addAlbum);
			panelNorteCentral.revalidate();
			panelNorteCentral.repaint();
		}
		PanelPerfil panel_dos = new PanelPerfil(usuario, usuarioAct);
		panelCentral.add(panel_dos, BorderLayout.CENTER);
		cardLayout.next(panelCentral);
		panelCentral.remove(0);
	}

}
