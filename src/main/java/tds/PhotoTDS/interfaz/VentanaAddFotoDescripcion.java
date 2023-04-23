package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import tds.PhotoTDS.Album;
import tds.PhotoTDS.Foto;
import tds.PhotoTDS.PhotoTDS;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class VentanaAddFotoDescripcion extends JFrame {

	private static final long serialVersionUID = 1L;
	private static String nombreFoto;
	private static int usuario;
	private static String album = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAddFotoDescripcion window = new VentanaAddFotoDescripcion(nombreFoto, usuario, album);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaAddFotoDescripcion(String nombreFoto, int usuario, String album) {
		VentanaAddFotoDescripcion.album = album;
		initialize(nombreFoto, usuario);
	}

	private void initialize(String nombreFoto, int usuario) {
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		//TODO REVISAR SI SOBRA TIEMPO
		setBounds(100, 100, 550, 350);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panelFotoComentario = new JPanel();
		getContentPane().add(panelFotoComentario, BorderLayout.CENTER);
		panelFotoComentario.setLayout(new BoxLayout(panelFotoComentario, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("");
		Image foto = new ImageIcon(System.getProperty("user.dir") + PhotoTDS.pathFotos + nombreFoto).getImage();
		
		
		JPanel panel_2 = new JPanel();
		panelFotoComentario.add(panel_2);
		lblNewLabel.setIcon(new ImageIcon(foto.getScaledInstance(300, 200, DO_NOTHING_ON_CLOSE)));
		panelFotoComentario.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panelFotoComentario.add(panel_1);
		
		JPanel panelComentario = new JPanel();
		panelFotoComentario.add(panelComentario);
		panelComentario.setLayout(new BoxLayout(panelComentario, BoxLayout.PAGE_AXIS));
		
		JLabel lblNewLabel_2 = new JLabel("Pon un título a la foto:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelComentario.add(lblNewLabel_2);
		
		JPanel panel_4 = new JPanel();
		panelComentario.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTextArea textArea = new JTextArea();
		panel_4.add(textArea);
		textArea.setLineWrap(true);
		
		JLabel lblNewLabel_1 = new JLabel("Añade una descripción:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelComentario.add(lblNewLabel_1);
		
		JPanel panel_5 = new JPanel();
		panelComentario.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		panel_5.add(textArea_1);
		
		
		JPanel panel_3 = new JPanel();
		panelFotoComentario.add(panel_3);
		
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Compartir");
		btnNewButton.addActionListener(ev -> {
			String descp = textArea_1.getText();
			ArrayList<String> hashtags = getHashtags(descp);
			if (hashtags == null || descp.length() > 120) {
				VentanaWarning vw = new VentanaWarning("La descripción sobrepasa los 120 caracteres, contiene más de 4 hashtags o algún hashtag sobrepasa el límite.");
				vw.setVisible(true);
			} else {
				Foto f = new Foto(textArea.getText(), descp, hashtags, usuario, nombreFoto);
				addFoto(f);
				controlador.addNotificacionSeguidores(usuario, f);
				if (!album.equals("")) {
					try {
						Album a = controlador.getAlbum(album, usuario);
						a.addFoto(f);
						controlador.modificarAlbum(a);
						controlador.addNotificacionSeguidores(usuario, a);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				VentanaPrincipal vP = null;
				try {
					vP = new VentanaPrincipal(controlador.getUsuario(usuario));
					vP.setVisible(true);
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(ev -> {
			VentanaAddFoto vAF = new VentanaAddFoto(usuario);
			vAF.setVisible(true);
			dispose();
		});
		panel.add(btnNewButton_1);
		
	}
	private ArrayList<String> getHashtags(String descripcion){
		Pattern pt = Pattern.compile("#(\\S+)");
		Matcher m = pt.matcher(descripcion);
		ArrayList<String> list = new ArrayList<String>();
		while(m.find()) {
			list.add(m.group(1));
		}
		
		// Se comprueba que los hashtags no pasen el límite
		if (list.stream()
				.anyMatch(h -> h.length() > 15) || list.size() > 4)
			return null;
		return list;
	}
	
	private void addFoto(Foto f) {
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		controlador.registrarFoto(f);
	}

}
