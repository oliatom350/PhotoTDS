package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tds.PhotoTDS.Foto;
import tds.PhotoTDS.Usuario;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import pulsador.Luz;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private static Usuario usuario;
	private JButton searchButton;
	private Luz xmlLoaderButton;
	private JPanel panelNorte;
	private JPanel panelNorteCentral;
	private JPanel panelNorteOeste;
	private JPanel panelNorteEste;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;

	
	ArrayList<Foto> fotos = new ArrayList<Foto>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal(usuario);
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
	public VentanaPrincipal(Usuario usuario) {
		this.usuario = usuario;
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
		panelNorte.add(panelNorteEste, BorderLayout.EAST);
		
		JButton addFoto = new JButton("");
		Image iconAddFoto = new ImageIcon(VentanaPrincipal.class.getResource("/images/iconoplus.png")).getImage();
		addFoto.setIcon(new ImageIcon(iconAddFoto.getScaledInstance(20, 20, DO_NOTHING_ON_CLOSE)));
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
		
		Foto f1 = new Foto("Arbol", "Vegetacion", new ArrayList<String>(), usuario.getNombre(), "/images/arbol.png");
		fotos.add(f1);
		Foto f3 = new Foto("Grecia", "Coloso", new ArrayList<String>(), usuario.getNombre(), "/images/colosorodas2.png");
		fotos.add(f3);
		Foto f2 = new Foto("Caballo", "Hola", new ArrayList<String>(), usuario.getNombre(), "/images/caballoatardecer.png");
		fotos.add(f2);
		
		
		JPanel panelCentralCentro = new JPanel();
		panelCentral.add(panelCentralCentro, BorderLayout.CENTER);
		//TODO Cuando tengamos el ArrayList de las fotos que vamos a mostrar, entonces sustituimos el primer 1 por el ArrayList.Size
		panelCentralCentro.setLayout(new GridLayout(fotos.size(), 1, 0, 8));
		
		for (Foto foto : fotos) {
			panelCentralCentro.add(new PanelItemFoto(foto));
		}
		
	}

}

