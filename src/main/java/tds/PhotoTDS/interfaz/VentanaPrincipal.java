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
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private static Usuario usuario;
	private JPanel panel_2;
	private JButton addFoto;
	private JTextField txtBuscaFotoUsuario;
	private JPanel panel_3;
	private JPanel panel_ListFotos;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_4;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panelSuperior = new JPanel();
		contentPane.add(panelSuperior);
		panelSuperior.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		panelSuperior.add(panel_2, BorderLayout.EAST);
		
		addFoto = new JButton("");
		Image iconAddFoto = new ImageIcon(VentanaPrincipal.class.getResource("/images/iconoplus.png")).getImage();
		addFoto.setIcon(new ImageIcon(iconAddFoto.getScaledInstance(20, 20, DO_NOTHING_ON_CLOSE)));
		addFoto.setBackground(Color.WHITE);
		panel_2.add(addFoto);
		
		txtBuscaFotoUsuario = new JTextField();
		txtBuscaFotoUsuario.setColumns(10);
		panel_2.add(txtBuscaFotoUsuario);
		
		panel_3 = new JPanel();
		panelSuperior.add(panel_3, BorderLayout.WEST);
		
		panel_ListFotos = new JPanel();
		panelSuperior.add(panel_ListFotos, BorderLayout.SOUTH);
		panel_ListFotos.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel = new JPanel();
		panel_ListFotos.add(panel);
		
		lblNewLabel = new JLabel("");
		iconAddFoto = new ImageIcon(VentanaPrincipal.class.getResource("/images/arbol.png")).getImage().getScaledInstance(50, 50, DO_NOTHING_ON_CLOSE);
		lblNewLabel.setIcon(new ImageIcon(iconAddFoto));
		panel.add(lblNewLabel);
		
		panel_1 = new JPanel();
		panel_ListFotos.add(panel_1);
		
		lblNewLabel_1 = new JLabel("");
		iconAddFoto = new ImageIcon(VentanaPrincipal.class.getResource("/images/leopardo.png")).getImage().getScaledInstance(50, 50, DO_NOTHING_ON_CLOSE);
		lblNewLabel_1.setIcon(new ImageIcon(iconAddFoto));
		panel_1.add(lblNewLabel_1);
		
		panel_4 = new JPanel();
		panel_ListFotos.add(panel_4);
		
		lblNewLabel_2 = new JLabel("");
		iconAddFoto = new ImageIcon(VentanaPrincipal.class.getResource("/images/madrid.png")).getImage().getScaledInstance(50, 50, DO_NOTHING_ON_CLOSE);
		lblNewLabel_2.setIcon(new ImageIcon(iconAddFoto));
		panel_4.add(lblNewLabel_2);
		
	}

}
