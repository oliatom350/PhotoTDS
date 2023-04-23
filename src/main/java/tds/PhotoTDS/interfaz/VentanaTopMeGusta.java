package tds.PhotoTDS.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import tds.PhotoTDS.Foto;
import tds.PhotoTDS.PhotoTDS;

@SuppressWarnings("serial")
public class VentanaTopMeGusta extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTopMeGusta frame = new VentanaTopMeGusta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaTopMeGusta() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Obtenemos las 10 fotos con mas me gusta
		ArrayList<Foto> fotos = PhotoTDS.getUnicaInstancia().getFotosTop(); 
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setPreferredSize(new Dimension(400, 120*fotos.size()));
		
		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		contentPane.add(scrollPane,BorderLayout.CENTER);
		
		for (Foto foto : fotos) {
			JPanel panel_1 = new JPanel();
			panel.add(panel_1);
			
			JLabel lblNewLabel = new JLabel("");
			Image icon = new ImageIcon(System.getProperty("user.dir")+PhotoTDS.pathFotos+foto.getPath()).getImage();
			panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			lblNewLabel.setIcon(new ImageIcon(icon.getScaledInstance(110, 80, java.awt.Image.SCALE_SMOOTH)));
			lblNewLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
                    
                }
			});
			panel_1.add(lblNewLabel);
			
			JPanel panel_2 = new JPanel();
			panel_1.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel(Integer.toString(foto.getMeGusta())  );
			panel_1.add(lblNewLabel_1);
		}
	}

}
