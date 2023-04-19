package tds.PhotoTDS.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;


public class VentanaBusquedaHashtag extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Map<String, Integer> hashtags;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBusquedaHashtag frame = new VentanaBusquedaHashtag(hashtags);
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
	public VentanaBusquedaHashtag(Map<String, Integer> hashtags) {
		initialize(hashtags);
	}
	
	private void initialize(Map<String, Integer> hashtags) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 350, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setPreferredSize(new Dimension(200, 40*hashtags.size()));
		
		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		contentPane.add(scrollPane,BorderLayout.CENTER);
		
		for (Map.Entry<String, Integer> entry : hashtags.entrySet()) {
			JPanel panel_1 = new JPanel();
			panel.add(panel_1);
			
			JLabel lblNewLabel = new JLabel(entry.getKey());
			panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			panel_1.add(lblNewLabel);
			
			JLabel separator = new JLabel("  ->  ");
			panel_1.add(separator);
			
			JLabel lblNewLabel_1 = new JLabel(entry.getValue().toString());
			panel_1.add(lblNewLabel_1);
		}
	}

}
