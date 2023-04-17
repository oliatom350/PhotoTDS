package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import tds.PhotoTDS.Foto;
import tds.PhotoTDS.PhotoTDS;

import java.awt.Font;

public class VentanaAddFotoComentario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String urlFoto;
	private static int usuario;
	private JTextField textField;
	private JTextField txtAsdfasdf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAddFotoComentario window = new VentanaAddFotoComentario(urlFoto, usuario);
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
	public VentanaAddFotoComentario(String urlFoto, int usuario) {
		initialize(urlFoto, usuario);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String urlFoto, int usuario) {
		setBounds(100, 100, 550, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		JPanel panelFotoComentario = new JPanel();
		getContentPane().add(panelFotoComentario, BorderLayout.CENTER);
		panelFotoComentario.setLayout(new BoxLayout(panelFotoComentario, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("");
		Image foto = new ImageIcon(VentanaAddFotoComentario.class.getResource("/images/"+urlFoto)).getImage();
		
		JPanel panel_2 = new JPanel();
		panelFotoComentario.add(panel_2);
		lblNewLabel.setIcon(new ImageIcon(foto.getScaledInstance(300, 200, DO_NOTHING_ON_CLOSE)));
		panelFotoComentario.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panelFotoComentario.add(panel_1);
		
		JPanel panelComenatrio = new JPanel();
		panelFotoComentario.add(panelComenatrio);
		panelComenatrio.setLayout(new BoxLayout(panelComenatrio, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_2 = new JLabel("Pon un título a la foto:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelComenatrio.add(lblNewLabel_2);
		
		txtAsdfasdf = new JTextField();
		panelComenatrio.add(txtAsdfasdf);
		txtAsdfasdf.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Añade una descripción:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelComenatrio.add(lblNewLabel_1);
		
		textField = new JTextField();
		panelComenatrio.add(textField);
		textField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panelFotoComentario.add(panel_3);
		
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Compartir");
		btnNewButton.addActionListener(ev -> {
			String descp = textField.getText();
			Foto f = new Foto(txtAsdfasdf.getText(), descp, getHashtags(descp), usuario, "/images/"+urlFoto);
			addFoto(f);
			dispose();
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
		return list;
	}
	
	private void addFoto(Foto f) {
		PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
		controlador.registrarFoto(f);
	}

}
