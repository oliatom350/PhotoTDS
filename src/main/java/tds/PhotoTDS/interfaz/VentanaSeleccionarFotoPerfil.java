package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
 
public class VentanaSeleccionarFotoPerfil extends JFrame {
 
    private JPanel contentPane;
    private JTextArea textArea;
    
    private File fichero;
    
    private static VentanaRegistro vr;
    private JPanel panelNorte;
    private JPanel panelCentral;
    private JPanel panelSur;
    private JTextField textField;
    private JButton btnSeleccionar;
    private JButton btnAceptar;
    private JButton btnCancelar;
 
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	VentanaSeleccionarFotoPerfil frame = new VentanaSeleccionarFotoPerfil(vr);
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
    public VentanaSeleccionarFotoPerfil(VentanaRegistro ventana) {
    	vr = ventana;
    	
        //Parametros asociados a la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 428, 211);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        panelNorte = new JPanel();
        contentPane.add(panelNorte, BorderLayout.NORTH);
        
        panelCentral = new JPanel();
        contentPane.add(panelCentral, BorderLayout.CENTER);
        
        textField = new JTextField();
        textField.setToolTipText("Inserta la ruta de la imagen");
        textField.setColumns(10);
        panelCentral.add(textField);
        
        btnSeleccionar = new JButton("Seleccionar");
        panelCentral.add(btnSeleccionar);
        btnSeleccionar.addActionListener(ev -> {
        	//Creamos el objeto JFileChooser
        	JFileChooser fc=new JFileChooser();
        	 
        	//Indicamos lo que podemos seleccionar
        	fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        	 
        	//Creamos el filtro
        	FileNameExtensionFilter filtroJPG = new FileNameExtensionFilter("*.jpg", "jpg");
        	FileNameExtensionFilter filtroJPEG = new FileNameExtensionFilter("*.jpeg", "jpeg");
        	FileNameExtensionFilter filtroPNG = new FileNameExtensionFilter("*.png", "png");
        	 
        	//Le indicamos el filtro
        	fc.setFileFilter(filtroJPG);
        	fc.setFileFilter(filtroJPEG);
        	fc.setFileFilter(filtroPNG);
        	 
        	//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
        	int seleccion=fc.showSaveDialog(contentPane);
        	 
        	//Si el usuario, pincha en aceptar
        	if(seleccion==JFileChooser.APPROVE_OPTION){
        	 
        	    //Seleccionamos el fichero
        	    fichero=fc.getSelectedFile();
        	 
        	    textField.setText(fichero.getAbsolutePath());
        	}
        });
        
        panelSur = new JPanel();
        contentPane.add(panelSur, BorderLayout.SOUTH);
        
        btnAceptar = new JButton("Aceptar");
        panelSur.add(btnAceptar);
        btnAceptar.addActionListener(ev -> {
        	vr.setRutaFoto(textField.getText());
        	this.dispose();
        });
        
        btnCancelar = new JButton("Cancelar");
        panelSur.add(btnCancelar);
        btnCancelar.addActionListener(ev -> {
        	this.dispose();
        });
        
        
    }
}