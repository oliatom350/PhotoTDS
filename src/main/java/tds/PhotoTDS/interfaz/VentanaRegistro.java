package tds.PhotoTDS.interfaz;

import javax.swing.*;
import javax.swing.border.*;

import tds.PhotoTDS.PhotoTDS;
import tds.PhotoTDS.Usuario;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class VentanaRegistro extends JFrame {

	
	private static final long serialVersionUID = -1897859737632306368L;
	
	private JPanel contentPane;
	private JTextField textField_Email;
	private JTextField textField_Nombre;
	private JTextField textField_Usuario;
	private JTextField textField_Contraseña;
	private JTextField textField_Dia;
	private JTextField textField_Mes;
	private JTextField textField_Año;
	public static Usuario usuario;
	
	//Inicialmente, rutaFoto y descripcion estarán vacías
	private String fotoPerfil = "";
	private String descripcion = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro(usuario);
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("deprecation")
	public VentanaRegistro(Usuario usuario) {
		VentanaRegistro.usuario = usuario;
		setResizable(false);
		setBounds(100, 100, 524, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
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
		
		//Inicialmente, al usuario se le asignará la ruta de la foto default
		fotoPerfil = "defaultUserProfile.jpg";
		
		JLabel EtiquetaFoto = new JLabel("Foto de perfil (opcional)");
		EtiquetaFoto.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panelFotoPerfil.add(EtiquetaFoto);
		
		JButton BotonAddFoto = new JButton("Añadir");
		panelFotoPerfil.add(BotonAddFoto);
		BotonAddFoto.addActionListener(ev -> {
			VentanaSeleccionarFotoPerfil vF = new VentanaSeleccionarFotoPerfil(this);
			vF.setVisible(true);
		});
		
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
			VentanaDescripcion vD = new VentanaDescripcion(this);
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
		
		//Deshabilita campos en el caso de editar perfil
		if(usuario != null) {
			textField_Usuario.setText(usuario.getNombre());
			textField_Usuario.setEditable(false);
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(usuario.getFechaNacimiento());
	        int day = calendar.get(Calendar.DAY_OF_MONTH);
	        int month = calendar.get(Calendar.MONTH) + 1;
	        int year = calendar.get(Calendar.YEAR);
			textField_Año.setText(Integer.toString(year+1900));
			textField_Año.setEditable(false);
			textField_Dia.setText(Integer.toString(day));
			textField_Dia.setEditable(false);
			textField_Mes.setText(Integer.toString(month));
			textField_Mes.setEditable(false);
			textField_Nombre.setText(usuario.getNombreCompleto());
			textField_Nombre.setEditable(false);
			textField_Email.setText(usuario.getEmail());
			textField_Email.setEditable(false);
			
			//Campos editables
			textField_Contraseña.setText(usuario.getPassword());
		}
		
		
		JButton BotonAceptar = new JButton("Aceptar");
		panelBotonesVentana.add(BotonAceptar);
		BotonAceptar.addActionListener(ev -> {
			//Comprobamos cada argumento
			if(checkStringNotNull(textField_Usuario.getText())) {
				showWarning("usuario");
				return;
			}
			if(checkStringNotNull(textField_Email.getText())) {
				showWarning("email");
				return;
			}
			if(checkStringNotNull(textField_Nombre.getText())) {
				showWarning("nombre");
				return;
			}
			if(checkStringNotNull(textField_Contraseña.getText())) {
				showWarning("contraseña");
				return;
			}
			
			PhotoTDS controlador = PhotoTDS.getUnicaInstancia();
			//Por defecto se registra al nuevo usuario como NO PREMIUM
			if(usuario == null) {
				controlador.registrarUsuario(new Usuario(textField_Usuario.getText(), 
					 						textField_Email.getText(),
					 						textField_Nombre.getText(),
											new Date(Integer.parseInt(textField_Año.getText())-1900,
													 Integer.parseInt(textField_Mes.getText())-1, 
													 Integer.parseInt(textField_Dia.getText())),
											false,
											textField_Contraseña.getText(),
											fotoPerfil,
											descripcion
					 						));
				llamaVentanaLogin();
			} else {
				if (descripcion != "") 
					usuario.setPresentacion(descripcion);
				usuario.setPassword(textField_Contraseña.getText());
				if (fotoPerfil == "defaultUserProfile.jpg")
					usuario.setFotoPerfil(fotoPerfil);
				controlador.modificarUsuario(usuario);
				this.dispose();
			}
		});
		
		JButton BotonCancelar = new JButton("Cancelar");
		panelBotonesVentana.add(BotonCancelar);
		BotonCancelar.addActionListener(ev -> {
			if (usuario == null)
				llamaVentanaLogin();
			else
				this.dispose();
		});
	}
	
	protected void setDescripcion(String presentacion) {
		descripcion = presentacion;
	}
	
	protected void setRutaFoto(String ruta) {
		fotoPerfil = ruta;
	}
	
	private boolean checkStringNotNull(String s) {
		if (s.equals("") || s.equals(null)) return true;
		else return false;
	}
	
	private void showWarning(String field) {
		VentanaWarning vw = new VentanaWarning("El campo de entrada "+field+ " es incorrecto!");
		vw.setLocationRelativeTo(null);
		vw.setVisible(true);
	}
	
	private void llamaVentanaLogin() {
		VentanaEntrada vE = new VentanaEntrada();
		vE.setLocationRelativeTo(null);
		vE.setVisible(true);
		this.dispose();
	}
}
