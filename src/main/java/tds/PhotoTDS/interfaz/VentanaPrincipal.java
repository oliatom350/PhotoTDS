package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tds.PhotoTDS.Usuario;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.FlowLayout;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private static Usuario usuario;
	private JTextField buscarUsuario;

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
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JButton addFoto = new JButton("");
		addFoto.setBackground(new Color(255, 255, 255));
		Image iconAddFoto = new ImageIcon(VentanaPrincipal.class.getResource("/images/iconoplus.png")).getImage();
		addFoto.setIcon( new ImageIcon(iconAddFoto.getScaledInstance(20, 20, DO_NOTHING_ON_CLOSE)));
		addFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(addFoto);
		
		buscarUsuario = new JTextField();
		panel.add(buscarUsuario);
		buscarUsuario.setColumns(10);
	}

}
