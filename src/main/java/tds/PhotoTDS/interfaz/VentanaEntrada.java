package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

//Comentario
public class VentanaEntrada extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField usuario;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEntrada window = new VentanaEntrada();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaEntrada() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("SansSerif", Font.PLAIN, 14));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panelInicioSesion = new JPanel();
		panelInicioSesion.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		frame.getContentPane().add(panelInicioSesion);
		panelInicioSesion.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panelInicioSesion.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("PhotoTDS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panelInicioSesion.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("Nombre de usuario o email:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_1);
		
		JPanel panel_9 = new JPanel();
		panel_2.add(panel_9);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		
		JLabel lblNewLabel_2_1 = new JLabel("Contraseña:");
		panel_2.add(lblNewLabel_2_1);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		
		usuario = new JTextField();
		usuario.setHorizontalAlignment(SwingConstants.CENTER);
		usuario.setColumns(10);
		panel_7.add(usuario);
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);
		
		password = new JTextField();
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setColumns(10);
		panel_7.add(password);
		
		JPanel panel_3 = new JPanel();
		panelInicioSesion.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton iniciarSesion = new JButton("Iniciar Sesión");
		iniciarSesion.setVerticalAlignment(SwingConstants.BOTTOM);
		iniciarSesion.addActionListener(ev -> {
				String nombreUsuario = usuario.getText();
				String passUsuario = password.getText();
				System.out.println("Usuario registrado: " + nombreUsuario + " " + passUsuario);	
			});
		
		iniciarSesion.setBackground(SystemColor.text);
		iniciarSesion.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(iniciarSesion);
		
		JPanel panelCrearCuenta = new JPanel();
		frame.getContentPane().add(panelCrearCuenta);
		panelCrearCuenta.setLayout(new BoxLayout(panelCrearCuenta, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		panelCrearCuenta.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel("¿Aún no tienes cuenta?");
		panel_4.add(lblNewLabel_2);
		
		JPanel panel_5 = new JPanel();
		panelCrearCuenta.add(panel_5);
		
		JButton crearCuenta = new JButton("Crear una nueva cuenta");
		crearCuenta.addActionListener(ev -> {
				VentanaRegistro vR = new VentanaRegistro();
				vR.setVisible(true);
				frame.dispose();
			});
		crearCuenta.setBackground(new Color(255, 255, 255));
		crearCuenta.setForeground(new Color(0, 0, 0));
		panel_5.add(crearCuenta);
		
	}


}
