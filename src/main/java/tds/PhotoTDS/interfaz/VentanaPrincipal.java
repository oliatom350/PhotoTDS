package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tds.PhotoTDS.Foto;
import tds.PhotoTDS.PhotoTDS;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import pulsador.Luz;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = -2940916222961478615L;
	
	private JPanel contentPane;
	private static int usuario;
	private JButton searchButton;
	private Luz xmlLoaderButton;
	private JPanel panelNorte;
	private JPanel panelNorteCentral;
	private JPanel panelNorteOeste;
	private JPanel panelNorteEste;
	//Array de fotos a mostrar en la ventana principal
	ArrayList<Foto> fotos = new ArrayList<Foto>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//El array 'fotos' debe inicializarse aquí
					VentanaPrincipal frame = new VentanaPrincipal(usuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaPrincipal(int user) {
		
		usuario = user;
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		
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
			icon = new ImageIcon(controlador.getUsuario(user).getFotoPerfil()).getImage();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		fotoPerfil.setIcon(new ImageIcon(icon.getScaledInstance(30, 30, DO_NOTHING_ON_CLOSE)));
		fotoPerfil.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				abrePerfil();
			}
		});
		panelNorteEste.add(fotoPerfil);
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
		panelNorteCentral.add(searchButton);
		
		xmlLoaderButton = new Luz();
		panelNorteCentral.add(xmlLoaderButton);
		
		JPanel panelCentral = new JPanel();
		JScrollPane scrollPane = new JScrollPane(panelCentral);
		panelCentral.setLayout(new BorderLayout(0, 0));
		contentPane.add(scrollPane,BorderLayout.CENTER);
		
		//TODO En vez de introducir estas fotos manualmente, debemos buscar de las fotos más recientes subidas entre el usuario y el seguidor
		Foto f1 = new Foto("Arbol", "Vegetacion", new ArrayList<String>(), usuario, "/images/arbol.png");
		fotos.add(f1);
		Foto f3 = new Foto("Grecia", "Coloso", new ArrayList<String>(), usuario, "/images/colosorodas2.png");
		fotos.add(f3);
		Foto f2 = new Foto("Caballo", "Hola", new ArrayList<String>(), usuario, "/images/caballoatardecer.png");
		fotos.add(f2);
		Foto f4 = new Foto("Pajaros", "Aves", new ArrayList<String>(), usuario, "/images/pajaros.png");
		fotos.add(f4);
		
		JPanel panelCentralCentro = new JPanel();
		panelCentral.add(panelCentralCentro, BorderLayout.CENTER);
		panelCentralCentro.setLayout(new GridLayout(fotos.size(), 1, 0, 8));
		
		for (Foto foto : fotos) {
			panelCentralCentro.add(new PanelItemFoto(foto, usuario));
		}
		
	}
	
	public void nuevaFoto() {
		VentanaAddFoto vaf = new VentanaAddFoto(usuario);
		vaf.setVisible(true);
	}
	
	public void abrePerfil() {
		//TODO ¿Cambiar aspecto de la VentanaPrincipal o abrir VentanaPerfil nueva? Abrir nueva tonto
	}

}
