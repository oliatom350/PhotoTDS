package tds.PhotoTDS.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import tds.PhotoTDS.Notificacion;
import tds.PhotoTDS.PhotoTDS;
import tds.PhotoTDS.Usuario;

public class VentanaNotificaciones extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Usuario usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaNotificaciones frame = new VentanaNotificaciones(usuario);
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
	public VentanaNotificaciones(Usuario usuario) {
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane.setLayout(new BorderLayout(0, 0));
		setResizable(false);
		
		//Obtenemos las notificaciones del usuario
		ArrayList<Notificacion> notificaciones = usuario.getNotificaciones();
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setPreferredSize(new Dimension(400, 30*notificaciones.size()));
		
		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		contentPane.add(scrollPane,BorderLayout.CENTER);
		setContentPane(contentPane);
		
		for(Notificacion n : notificaciones) {
			JPanel panel_1 = new JPanel();
			panel.add(panel_1);
			panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			JLabel lblNewLabel = new JLabel(n.getMensaje());
			
			JLabel fecha = new JLabel(new SimpleDateFormat("dd-MM-yyyy hh:mm").format(n.getFecha()));
			
			panel_1.add(fecha);
			panel_1.add(lblNewLabel);
			
			
			JButton borrar = new JButton("Borrar");
			borrar.addActionListener(ev -> {
				panel.remove(panel_1);
				PhotoTDS.getUnicaInstancia().eliminarUsuarioNotificacion(usuario, n);
			});
			
			panel_1.add(borrar);
		}
	}

}
