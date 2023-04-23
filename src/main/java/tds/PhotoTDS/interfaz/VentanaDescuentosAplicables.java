package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import tds.PhotoTDS.Descuento;
import tds.PhotoTDS.DescuentoEdad;
import tds.PhotoTDS.Usuario;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class VentanaDescuentosAplicables extends JFrame{

	private JPanel contentPane;
	private static Usuario user;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDescuentosAplicables frame = new VentanaDescuentosAplicables(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaDescuentosAplicables(Usuario user) {
		initialize(user);
	}
	
	private void initialize(Usuario usuario) {
		user = usuario;
		HashSet<Descuento> descuentos = usuario.getDescuentos();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 200, 350, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setPreferredSize(new Dimension(200, 120*descuentos.size()));
		
		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		contentPane.add(scrollPane,BorderLayout.CENTER);
		
		if (descuentos.size() == 0) {
			VentanaPagoPremium vpp = new VentanaPagoPremium(usuario, Descuento.PRECIO_PREMIUM);
			vpp.setVisible(true);
			this.dispose();
		}
		for (Descuento descuento : descuentos) {
			JPanel panel_1 = new JPanel();
			
			JLabel lblNewLabel;
			double precio = descuento.getPrecioPremium();
			if (descuento instanceof DescuentoEdad) {
				lblNewLabel = new JLabel("Descuento por edad: 10 -> "+ precio);
				lblNewLabel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
	                    VentanaPagoPremium vpp = new VentanaPagoPremium(usuario, precio);
						vpp.setVisible(true);
	                }
				});
			} else {
				lblNewLabel = new JLabel("Descuento por número de likes: 10 -> "+ precio);
				lblNewLabel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
	                    VentanaPagoPremium vpp = new VentanaPagoPremium(usuario, precio);
						vpp.setVisible(true);
	                }
				});
			}	
			panel_1.add(lblNewLabel);
			
			panel.add(panel_1);
		}
	}
}
