package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class VentanaDescripcion extends JFrame {

	private JPanel contentPane;

	private static VentanaRegistro vr;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDescripcion frame = new VentanaDescripcion(vr);
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
	public VentanaDescripcion(VentanaRegistro ventana) {
		vr = ventana;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panelNorte.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("PhotoTDS");
		lblNewLabel_1.setFont(new Font("Segoe UI Historic", Font.BOLD, 22));
		panel.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panelNorte.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Introduce una descripción para tu perfil:");
		lblNewLabel.setFont(new Font("Segoe UI Historic", Font.PLAIN, 17));
		panel_2.add(lblNewLabel);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panelCentral.add(panel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		panelCentral.add(textArea);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnFinalizar = new JButton("Finalizar");
		panelSur.add(btnFinalizar);
		btnFinalizar.addActionListener(ev -> {
			vr.setDescripcion(textArea.getText());
			this.setVisible(false);
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		panelSur.add(btnCancelar);
		btnCancelar.addActionListener(ev -> {
			this.dispose();
		});
	}
}
