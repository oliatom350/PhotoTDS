package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class VentanaWarning extends JFrame {


	private static final long serialVersionUID = -1217175186146731268L;
	private JPanel contentPane;
	static String output;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaWarning frame = new VentanaWarning(output);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaWarning(String out) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 256);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel output = new JLabel(out);
		output.setHorizontalAlignment(SwingConstants.CENTER);
		output.setBounds(10, 48, 314, 115);
		contentPane.add(output);
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(ev -> {
			this.dispose();
		});
		aceptar.setBounds(126, 188, 85, 21);
		contentPane.add(aceptar);
	}
}
