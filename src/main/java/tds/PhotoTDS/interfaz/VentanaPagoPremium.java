package tds.PhotoTDS.interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tds.PhotoTDS.PhotoTDS;
import tds.PhotoTDS.Usuario;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class VentanaPagoPremium extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Usuario user;
	private static double precio;

	public static void main(String[] args) {
		try {
			VentanaPagoPremium dialog = new VentanaPagoPremium(user, precio);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VentanaPagoPremium(Usuario user, double precio) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("El precio a pagar tras el descuento es de "+ precio);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(5, 5, 426, 222);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(ev -> {
					user.setPremium(true);
					PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
					controlador.modificarUsuario(user);
					VentanaWarning vw = new VentanaWarning("¡" + user.getNombre() + " es ahora premium!");
					vw.setVisible(true);
					this.dispose();
				});
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(ev -> {
					this.dispose();
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
