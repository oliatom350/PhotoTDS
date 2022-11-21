package tds.PhotoTDS.interfaz;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

import java.awt.*;

public class VentanaRegistro extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
					frame.pack();
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
	public VentanaRegistro() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS));
		
		JPanel panel_11 = new JPanel();
		panelNorte.add(panel_11);
		
		JLabel lblNewLabel = new JLabel("PhotoTDS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 22));
		panel_11.add(lblNewLabel);
		
		JPanel panel_12 = new JPanel();
		panel_12.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panelNorte.add(panel_12);
		
		JLabel lblNewLabel_8 = new JLabel("Ventana de registro");
		lblNewLabel_8.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
		panel_12.add(lblNewLabel_8);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentralIOeste = new JPanel();
		panelCentralIOeste.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCentral.add(panelCentralIOeste, BorderLayout.WEST);
		panelCentralIOeste.setLayout(new BoxLayout(panelCentralIOeste, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelCentralIOeste.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setColumns(10);
		panel.add(textField);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelCentralIOeste.add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre completo");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setColumns(10);
		panel_1.add(textField_1);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setVgap(10);
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panelCentralIOeste.add(panel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre usuario");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_2.setHorizontalAlignment(SwingConstants.LEFT);
		textField_2.setColumns(10);
		panel_2.add(textField_2);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.TRAILING);
		panelCentralIOeste.add(panel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Contraseña");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel_3.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_3.setHorizontalAlignment(SwingConstants.LEFT);
		textField_3.setColumns(10);
		panel_3.add(textField_3);
		
		JPanel panelCentralCentral = new JPanel();
		panelCentralCentral.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCentral.add(panelCentralCentral, BorderLayout.CENTER);
		panelCentralCentral.setLayout(new BoxLayout(panelCentralCentral, BoxLayout.Y_AXIS));
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_6.getLayout();
		panelCentralCentral.add(panel_6);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha de nacimiento");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel_6.add(lblNewLabel_5);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_6.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_8.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		panel_7.add(panel_8);
		
		JLabel lblNewLabel_6 = new JLabel("Día");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel_8.add(lblNewLabel_6);
		
		textField_4 = new JTextField();
		textField_4.setColumns(2);
		panel_8.add(textField_4);
		
		JPanel panel_8_1 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_8_1.getLayout();
		flowLayout_5.setAlignment(FlowLayout.RIGHT);
		panel_7.add(panel_8_1);
		
		JLabel lblNewLabel_6_1 = new JLabel("Mes");
		lblNewLabel_6_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel_8_1.add(lblNewLabel_6_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(2);
		panel_8_1.add(textField_5);
		
		JPanel panel_8_2 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_8_2.getLayout();
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
		panel_7.add(panel_8_2);
		
		JLabel lblNewLabel_6_2 = new JLabel("Año");
		lblNewLabel_6_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6_2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panel_8_2.add(lblNewLabel_6_2);
		
		textField_6 = new JTextField();
		textField_6.setColumns(2);
		panel_8_2.add(textField_6);
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panel_9.getLayout();
		flowLayout_8.setAlignment(FlowLayout.RIGHT);
		panelCentralCentral.add(panel_9);
		
		JLabel lblNewLabel_7 = new JLabel("Foto de perfil (opcional)");
		lblNewLabel_7.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel_9.add(lblNewLabel_7);
		
		JButton btnNewButton_2 = new JButton("Añadir");
		try {
		    Image img = ImageIO.read(getClass().getResource("images/png.png"));
		    btnNewButton_2.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		panel_9.add(btnNewButton_2);
		
		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) panel_10.getLayout();
		flowLayout_9.setAlignment(FlowLayout.RIGHT);
		panelCentralCentral.add(panel_10);
		
		JLabel lblNewLabel_7_1 = new JLabel("Descripción (opcional)");
		lblNewLabel_7_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel_10.add(lblNewLabel_7_1);
		
		JButton btnNewButton_3 = new JButton("Añadir");
		panel_10.add(btnNewButton_3);
		
		JPanel panelCentralNorte = new JPanel();
		panelCentral.add(panelCentralNorte, BorderLayout.NORTH);
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new BoxLayout(panelSur, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		panelSur.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JTextPane txtRegistro = new JTextPane();
		txtRegistro.setText("¡Regístrate para navegar por el pictórico mundo de PhotoTDS!");
		txtRegistro.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtRegistro.setEditable(false);
		txtRegistro.setBackground(SystemColor.menu);
		panel_4.add(txtRegistro);
		
		JPanel panel_5 = new JPanel();
		panelSur.add(panel_5);
		
		JButton btnNewButton = new JButton("Aceptar");
		panel_5.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		panel_5.add(btnNewButton_1);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
	}

}
