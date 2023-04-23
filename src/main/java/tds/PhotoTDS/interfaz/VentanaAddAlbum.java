package tds.PhotoTDS.interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tds.PhotoTDS.Album;
import tds.PhotoTDS.PhotoTDS;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class VentanaAddAlbum extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private static int usuario;
	private JTextField textField;
	private JTextArea textArea;
	public static void main(String[] args) {
		try {
			VentanaAddAlbum dialog = new VentanaAddAlbum(usuario);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaAddAlbum(int user) {
		usuario = user;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel lblNewLabel = new JLabel("Introduce un nombre para tu álbum");
					lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
					panel_1.add(lblNewLabel);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
			}
			{
				textField = new JTextField();
				textField.setColumns(30);
				panel.add(textField);
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel lblNewLabel = new JLabel("Introduce una descripción para tu álbum");
					lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
					panel_1.add(lblNewLabel);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					textArea = new JTextArea();
					textArea.setWrapStyleWord(true);
					textArea.setTabSize(4);
					textArea.setRows(6);
					textArea.setLineWrap(true);
					textArea.setColumns(52);
					panel_1.add(textArea);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(ev -> {
					PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
					ArrayList<Album> albumes = new ArrayList<>();
					try {
						albumes = controlador.getAlbumesUsuario(usuario);
					} catch (Exception e) {
						e.printStackTrace();
					}
					boolean existeAlbum = albumes.stream()
						   .anyMatch(a -> a.getTitulo().equals(textField.getText()));
					
					if (existeAlbum) {
						VentanaWarning vw = new VentanaWarning("Ya existe un álbum con ese nombre");
						vw.setVisible(true);
					} else {
						controlador.registrarAlbum(new Album(textField.getText(), textArea.getText(), new ArrayList<>(), usuario, new LinkedList<>()));
						VentanaAddFoto vaf = new VentanaAddFoto(usuario, textField.getText());
						vaf.setVisible(true);
						this.dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
