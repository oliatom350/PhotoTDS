package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;

import tds.PhotoTDS.Album;
import tds.PhotoTDS.PhotoTDS;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VentanaAddFoto extends JFrame{

	private static final long serialVersionUID = 1L;
	private static int usuario;
	private static String album = "";
	
	//private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAddFoto window = new VentanaAddFoto(usuario);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaAddFoto(int usuario) {
		initialize(usuario);
	}
	
	public VentanaAddFoto(int usuario, String album) {
		VentanaAddFoto.album = album;
		initialize(usuario);
	}

	@SuppressWarnings("serial")
	private void initialize(int usuario) {
		setBounds(100, 100, 550, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		GridBagConstraints gbc_editorPane_1_1 = new GridBagConstraints();
		gbc_editorPane_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_editorPane_1_1.gridx = 4;
		gbc_editorPane_1_1.gridy = 0;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{155, 223, 0};
		gridBagLayout.rowHeights = new int[]{65, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setAlignmentX(5.0f);
		gbc_editorPane_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_editorPane_1_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_editorPane_1_1.gridx = 1;
		gbc_editorPane_1_1.gridy = 1;
		getContentPane().add(editorPane, gbc_editorPane_1_1);
		editorPane.setContentType("text/html");
		editorPane.setText("<h1>Agregar Foto</h1><p>Comparte una foto con tus seguidores. <br> Puedes arrastrar el fichero aquí. </p>");
		editorPane.setEditable(false);
		editorPane.setDropTarget(new DropTarget() {
			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					@SuppressWarnings("unchecked")
					List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
					if (droppedFiles.size() == 1) {
						File droppedFile = droppedFiles.get(0);
						PhotoTDS.copyFile(droppedFile);
						addFotoDescripcion(droppedFile.getName(), usuario);
					}
				}catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		
		
		JButton btnNewButton = new JButton("Selecciona una foto de tu ordenador");
		btnNewButton.addActionListener(ev -> {
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(getContentPane());
			File currentFile = chooser.getSelectedFile();
			try {
				PhotoTDS.copyFile(currentFile);
				addFotoDescripcion(currentFile.getName(), usuario);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 3;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
	}
	
	public void addFotoDescripcion(String nombreFoto, int idUsuario) {
		VentanaAddFotoDescripcion vA = new VentanaAddFotoDescripcion(nombreFoto, idUsuario, album);
		vA.setVisible(true);
		dispose();
	}
}
