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
	private JPanel panel_ListFotos;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel panelNorte;
	private JPanel panelCentral2;
	private JButton addFoto;
	private JTextField textField;
	private JPanel panelOeste2;
	private JPanel panelEste2;
	private JButton searchButton;
	private Luz xmlLoaderButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel1;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

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
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panelSuperior = new JPanel();
		contentPane.add(panelSuperior);
		panelSuperior.setLayout(new BorderLayout(0, 0));
				
		panel_ListFotos = new JPanel();
		
		scrollPane = new JScrollPane(panel_ListFotos);
		scrollPane.setPreferredSize(new Dimension(360, 220));
		panelSuperior.add(scrollPane, BorderLayout.CENTER);
		GridBagLayout gbl_panel_ListFotos = new GridBagLayout();
		gbl_panel_ListFotos.columnWidths = new int[] {524, 0};
		gbl_panel_ListFotos.rowHeights = new int[] {105, 105, 0};
		gbl_panel_ListFotos.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_ListFotos.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel_ListFotos.setLayout(gbl_panel_ListFotos);
		
		//Metemos el bucle
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel_ListFotos.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("");
		panel.add(lblNewLabel, BorderLayout.WEST);
		Image iconAddFoto = new ImageIcon(VentanaPrincipal.class.getResource("/images/arbol.png")).getImage().getScaledInstance(100, 100, DO_NOTHING_ON_CLOSE);
		lblNewLabel.setIcon(new ImageIcon(iconAddFoto));
		
		panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		panel_4 = new JPanel();
		panel_3.add(panel_4);
		
		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel_4.add(btnNewButton);
		
		btnNewButton_1 = new JButton("New button");
		panel_4.add(btnNewButton_1);
		
		lblNewLabel_1 = new JLabel("New label");
		panel_4.add(lblNewLabel_1);
		
		panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lblNewLabel_2 = new JLabel("New label");
		panel_5.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("New label");
		panel_5.add(lblNewLabel_3);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		panel_ListFotos.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		
		lblNewLabel1 = new JLabel("");
		panel_1.add(lblNewLabel1, BorderLayout.WEST);
		iconAddFoto = new ImageIcon(VentanaPrincipal.class.getResource("/images/mausoleo.png")).getImage().getScaledInstance(100, 100, DO_NOTHING_ON_CLOSE);
		lblNewLabel1.setIcon(new ImageIcon(iconAddFoto));
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		panel_ListFotos.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel2 = new JLabel("");
		panel_2.add(lblNewLabel2, BorderLayout.WEST);
		iconAddFoto = new ImageIcon(VentanaPrincipal.class.getResource("/images/zeus.png")).getImage().getScaledInstance(100, 100, DO_NOTHING_ON_CLOSE);
		lblNewLabel2.setIcon(new ImageIcon(iconAddFoto));
		
		panelNorte = new JPanel();
		panelNorte.setLayout(new BorderLayout(0, 0));
		panelSuperior.add(panelNorte, BorderLayout.NORTH);
		
		panelCentral2 = new JPanel();
		panelNorte.add(panelCentral2, BorderLayout.CENTER);
		
		addFoto = new JButton("");
		iconAddFoto = new ImageIcon(VentanaPrincipal.class.getResource("/images/iconoplus.png")).getImage();
		addFoto.setIcon(new ImageIcon(iconAddFoto.getScaledInstance(20, 20, DO_NOTHING_ON_CLOSE)));
		panelCentral2.add(addFoto);
		
		textField = new JTextField();
		textField.setColumns(10);
		panelCentral2.add(textField);
		
		searchButton = new JButton("Insertar lupa");
		iconAddFoto = new ImageIcon(VentanaPrincipal.class.getResource("/images/iconolupa.png")).getImage();
		searchButton.setIcon(new ImageIcon(iconAddFoto.getScaledInstance(15, 15, DO_NOTHING_ON_CLOSE)));
		panelCentral2.add(searchButton);
		
		xmlLoaderButton = new Luz();
		panelCentral2.add(xmlLoaderButton);
		
		panelOeste2 = new JPanel();
		panelNorte.add(panelOeste2, BorderLayout.WEST);
		
		panelEste2 = new JPanel();
		panelNorte.add(panelEste2, BorderLayout.EAST);
		
	}

}
