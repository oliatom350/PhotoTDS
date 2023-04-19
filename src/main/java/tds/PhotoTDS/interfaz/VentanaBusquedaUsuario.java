package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import tds.PhotoTDS.Usuario;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class VentanaBusquedaUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ArrayList<Usuario> usuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBusquedaUsuario frame = new VentanaBusquedaUsuario(usuarios);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaBusquedaUsuario(ArrayList<Usuario> usuarios) {
		initialize(usuarios);
	}
	
	private void initialize(ArrayList<Usuario> usuarios) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 200, 350, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setPreferredSize(new Dimension(200, 120*usuarios.size()));
		
		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		contentPane.add(scrollPane,BorderLayout.CENTER);
		
		/*JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		//Prueba----------
		String nUsuario = "Jose Antonio Sánchez";
		String pathFoto = "/images/SUUU.png";
		//Prueba----------
		
		JLabel lblNewLabel = new JLabel("");
		Image icon = new ImageIcon(VentanaBusquedaUsuario.class.getResource(pathFoto)).getImage();
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lblNewLabel.setIcon(new ImageIcon(icon.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH)));
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(nUsuario);
		panel_1.add(lblNewLabel_1);*/
		
		
		
		for (Usuario usuario : usuarios) {
			JPanel panel_1 = new JPanel();
			panel.add(panel_1);
			
			JLabel lblNewLabel = new JLabel("");
			Image icon = new ImageIcon(VentanaBusquedaUsuario.class.getResource(usuario.getFotoPerfil())).getImage();
			panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			lblNewLabel.setIcon(new ImageIcon(icon.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH)));
			panel_1.add(lblNewLabel);
			
			JPanel panel_2 = new JPanel();
			panel_1.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel(usuario.getNombre());
			panel_1.add(lblNewLabel_1);
		}
		
	}

}
