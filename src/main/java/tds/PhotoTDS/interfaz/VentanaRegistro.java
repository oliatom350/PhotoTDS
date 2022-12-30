package tds.PhotoTDS.interfaz;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class VentanaRegistro extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Email;
	private JTextField textField_Nombre;
	private JTextField textField_Usuario;
	private JTextField textField_Contraseña;
	private JTextField textField_Dia;
	private JTextField textField_Mes;
	private JTextField textField_Año;

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
		
		JPanel PanelTitulo = new JPanel();
		panelNorte.add(PanelTitulo);
		
		JLabel TituloVentana = new JLabel("PhotoTDS");
		TituloVentana.setHorizontalAlignment(SwingConstants.CENTER);
		TituloVentana.setFont(new Font("Segoe UI Historic", Font.BOLD, 22));
		PanelTitulo.add(TituloVentana);
		
		JPanel PanelSubtitulo = new JPanel();
		PanelSubtitulo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panelNorte.add(PanelSubtitulo);
		
		JLabel SubtituloVentana = new JLabel("Ventana de registro");
		SubtituloVentana.setVerticalAlignment(SwingConstants.TOP);
		SubtituloVentana.setHorizontalAlignment(SwingConstants.CENTER);
		SubtituloVentana.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
		PanelSubtitulo.add(SubtituloVentana);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentralIOeste = new JPanel();
		panelCentralIOeste.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCentral.add(panelCentralIOeste, BorderLayout.WEST);
		panelCentralIOeste.setLayout(new BoxLayout(panelCentralIOeste, BoxLayout.Y_AXIS));
		
		JPanel panelEmail = new JPanel();
		FlowLayout fl_panelEmail = (FlowLayout) panelEmail.getLayout();
		fl_panelEmail.setAlignment(FlowLayout.RIGHT);
		panelCentralIOeste.add(panelEmail);
		
		JLabel EtiquetaEmail = new JLabel("Email");
		EtiquetaEmail.setHorizontalAlignment(SwingConstants.LEFT);
		EtiquetaEmail.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panelEmail.add(EtiquetaEmail);
		
		textField_Email = new JTextField();
		textField_Email.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_Email.setHorizontalAlignment(SwingConstants.LEFT);
		textField_Email.setColumns(10);
		panelEmail.add(textField_Email);
		
		JPanel panelNombre = new JPanel();
		FlowLayout fl_panelNombre = (FlowLayout) panelNombre.getLayout();
		fl_panelNombre.setVgap(10);
		fl_panelNombre.setAlignment(FlowLayout.RIGHT);
		panelCentralIOeste.add(panelNombre);
		
		JLabel EtiquetaNombre = new JLabel("Nombre completo");
		EtiquetaNombre.setHorizontalAlignment(SwingConstants.LEFT);
		EtiquetaNombre.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panelNombre.add(EtiquetaNombre);
		
		textField_Nombre = new JTextField();
		textField_Nombre.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_Nombre.setHorizontalAlignment(SwingConstants.LEFT);
		textField_Nombre.setColumns(10);
		panelNombre.add(textField_Nombre);
		
		JPanel panelUsuario = new JPanel();
		FlowLayout fl_panelUsuario = (FlowLayout) panelUsuario.getLayout();
		fl_panelUsuario.setVgap(10);
		fl_panelUsuario.setAlignment(FlowLayout.RIGHT);
		panelCentralIOeste.add(panelUsuario);
		
		JLabel EtiquetaUsuario = new JLabel("Nombre usuario");
		EtiquetaUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		EtiquetaUsuario.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panelUsuario.add(EtiquetaUsuario);
		
		textField_Usuario = new JTextField();
		textField_Usuario.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_Usuario.setHorizontalAlignment(SwingConstants.LEFT);
		textField_Usuario.setColumns(10);
		panelUsuario.add(textField_Usuario);
		
		JPanel panelContraseña = new JPanel();
		FlowLayout fl_panelContraseña = (FlowLayout) panelContraseña.getLayout();
		fl_panelContraseña.setAlignment(FlowLayout.TRAILING);
		panelCentralIOeste.add(panelContraseña);
		
		JLabel EtiquetaContraseña = new JLabel("Contraseña");
		EtiquetaContraseña.setHorizontalAlignment(SwingConstants.LEFT);
		EtiquetaContraseña.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panelContraseña.add(EtiquetaContraseña);
		
		textField_Contraseña = new JTextField();
		textField_Contraseña.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_Contraseña.setHorizontalAlignment(SwingConstants.LEFT);
		textField_Contraseña.setColumns(10);
		panelContraseña.add(textField_Contraseña);
		
		JPanel panelCentralCentral = new JPanel();
		panelCentralCentral.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCentral.add(panelCentralCentral, BorderLayout.CENTER);
		panelCentralCentral.setLayout(new BoxLayout(panelCentralCentral, BoxLayout.Y_AXIS));
		
		JPanel panelFecha = new JPanel();
		panelCentralCentral.add(panelFecha);
		
		JLabel EtiquetaFecha = new JLabel("Fecha de nacimiento");
		EtiquetaFecha.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panelFecha.add(EtiquetaFecha);
		
		JPanel panelFormatoFecha = new JPanel();
		panelFormatoFecha.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelFecha.add(panelFormatoFecha);
		panelFormatoFecha.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelDia = new JPanel();
		FlowLayout fl_panelDia = (FlowLayout) panelDia.getLayout();
		fl_panelDia.setAlignment(FlowLayout.RIGHT);
		panelFormatoFecha.add(panelDia);
		
		JLabel EtiquetaDia = new JLabel("Día");
		EtiquetaDia.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panelDia.add(EtiquetaDia);
		
		textField_Dia = new JTextField();
		textField_Dia.setColumns(2);
		panelDia.add(textField_Dia);
		
		JPanel panelMes = new JPanel();
		FlowLayout fl_panelMes = (FlowLayout) panelMes.getLayout();
		fl_panelMes.setAlignment(FlowLayout.RIGHT);
		panelFormatoFecha.add(panelMes);
		
		JLabel EtiquetaMes = new JLabel("Mes");
		EtiquetaMes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panelMes.add(EtiquetaMes);
		
		textField_Mes = new JTextField();
		textField_Mes.setColumns(2);
		panelMes.add(textField_Mes);
		
		JPanel panelAño = new JPanel();
		FlowLayout fl_panelAño = (FlowLayout) panelAño.getLayout();
		fl_panelAño.setAlignment(FlowLayout.RIGHT);
		panelFormatoFecha.add(panelAño);
		
		JLabel EtiquetaAño = new JLabel("Año");
		EtiquetaAño.setHorizontalAlignment(SwingConstants.LEFT);
		EtiquetaAño.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panelAño.add(EtiquetaAño);
		
		textField_Año = new JTextField();
		textField_Año.setColumns(2);
		panelAño.add(textField_Año);
		
		JPanel panelFotoPerfil = new JPanel();
		FlowLayout fl_panelFotoPerfil = (FlowLayout) panelFotoPerfil.getLayout();
		fl_panelFotoPerfil.setAlignment(FlowLayout.RIGHT);
		panelCentralCentral.add(panelFotoPerfil);
		
		JLabel EtiquetaFoto = new JLabel("Foto de perfil (opcional)");
		EtiquetaFoto.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panelFotoPerfil.add(EtiquetaFoto);
		
		JButton BotonAddFoto = new JButton("Añadir");
		try {
		    Image img = ImageIO.read(getClass().getResource("images/png.png"));
		    BotonAddFoto.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		panelFotoPerfil.add(BotonAddFoto);
		
		JPanel panelDescripcion = new JPanel();
		FlowLayout fl_panelDescripcion = (FlowLayout) panelDescripcion.getLayout();
		fl_panelDescripcion.setAlignment(FlowLayout.RIGHT);
		panelCentralCentral.add(panelDescripcion);
		
		JLabel EtiquetaDescripcion = new JLabel("Descripción (opcional)");
		EtiquetaDescripcion.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panelDescripcion.add(EtiquetaDescripcion);
		
		JButton BotonAddDescripcion = new JButton("Añadir");
		panelDescripcion.add(BotonAddDescripcion);
		BotonAddDescripcion.addActionListener(ev -> {
			VentanaDescripcion vD = new VentanaDescripcion();
			vD.setVisible(true);
		});
		
		JPanel panelCentralNorte = new JPanel();
		panelCentral.add(panelCentralNorte, BorderLayout.NORTH);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new BoxLayout(panelSur, BoxLayout.Y_AXIS));
		
		JPanel panelTextoRegistro = new JPanel();
		panelSur.add(panelTextoRegistro);
		panelTextoRegistro.setLayout(new BoxLayout(panelTextoRegistro, BoxLayout.X_AXIS));
		
		JTextPane txtRegistro = new JTextPane();
		txtRegistro.setText("¡Regístrate para navegar por el pictórico mundo de PhotoTDS!");
		txtRegistro.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtRegistro.setEditable(false);
		txtRegistro.setBackground(SystemColor.menu);
		panelTextoRegistro.add(txtRegistro);
		
		JPanel panelBotonesVentana = new JPanel();
		panelSur.add(panelBotonesVentana);
		
		JButton BotonAceptar = new JButton("Aceptar");
		panelBotonesVentana.add(BotonAceptar);
		
		JButton BotonCancelar = new JButton("Cancelar");
		panelBotonesVentana.add(BotonCancelar);
		BotonCancelar.addActionListener(ev -> {
			VentanaEntrada vE = new VentanaEntrada();
			vE.setLocationRelativeTo(null);
			vE.setVisible(true);
			this.dispose();
		});
	}

}
