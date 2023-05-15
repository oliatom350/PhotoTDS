package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import tds.PhotoTDS.Comentario;
import tds.PhotoTDS.Foto;
import tds.PhotoTDS.PhotoTDS;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class VentanaAddComentario extends JFrame {

	private JPanel contentPane;
	
	private static int usuario;
	private static Foto foto;
	private static int width = 450;
	private static int height = 300;
	
	private final int longitudMaxima = 120;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAddComentario frame = new VentanaAddComentario(usuario, foto);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaAddComentario(int user, Foto f) {
		usuario = user;
		foto = f;
		
		setBounds(100, 100, width, height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
		
		JLabel lblNewLabel = new JLabel("Comparte un comentario (máx. 120 caracteres):");
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
			if (VerificaLongitud(textArea.getText())) {
				Comentario coment = new Comentario(textArea.getText(), user);
				PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
				controlador.addComentario(coment, f);
				try {
					controlador.addUsuarioNotificacion(controlador.getUsuario(foto.getUsuario()), null, "El usuario " + controlador.getUsuario(user).getNombre() + " ha comentado '" + coment.getTexto() + "' en la foto " + f.getTitulo());
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.setVisible(false);
			} else {
				textArea.setText(textArea.getText().substring(0, longitudMaxima));
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		panelSur.add(btnCancelar);
		btnCancelar.addActionListener(ev -> {
			this.dispose();
		});
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		panelOeste.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		JLabel fotoLabel = new JLabel("");
		Image icon = new ImageIcon(foto.getPath()).getImage();
		fotoLabel.setIcon(new ImageIcon(icon.getScaledInstance(width/2, height/2, Image.SCALE_SMOOTH)));
		panelOeste.add(fotoLabel);
	}
	
	public boolean VerificaLongitud(String text) {
		if (text.length() > longitudMaxima) {
			VentanaWarning vent = new VentanaWarning("Longitud de comentario superada");
			vent.setVisible(true);
			return false;
		}
		return true;
	}
}
