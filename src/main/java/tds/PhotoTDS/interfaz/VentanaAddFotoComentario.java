package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class VentanaAddFotoComentario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String urlFoto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAddFotoComentario window = new VentanaAddFotoComentario(urlFoto);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaAddFotoComentario(String urlFoto) {
		initialize(urlFoto);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String urlFoto) {
		setBounds(100, 100, 550, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		JPanel panelFotoComentario = new JPanel();
		getContentPane().add(panelFotoComentario, BorderLayout.CENTER);
		panelFotoComentario.setLayout(new BoxLayout(panelFotoComentario, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("New label");
		Image foto = new ImageIcon(VentanaAddFotoComentario.class.getResource(urlFoto)).getImage();
		lblNewLabel.setIcon(new ImageIcon(foto.getScaledInstance(20, 20, DO_NOTHING_ON_CLOSE)));
		panelFotoComentario.add(lblNewLabel);
		
		JPanel panelComenatrio = new JPanel();
		panelFotoComentario.add(panelComenatrio);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Compartir");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		panel.add(btnNewButton_1);
		
		
	}

}
