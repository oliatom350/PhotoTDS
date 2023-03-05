package tds.PhotoTDS.interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.awt.Component;

public class VentanaAddFoto {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAddFoto window = new VentanaAddFoto();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaAddFoto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		GridBagConstraints gbc_editorPane_1_1 = new GridBagConstraints();
		gbc_editorPane_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_editorPane_1_1.gridx = 4;
		gbc_editorPane_1_1.gridy = 0;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{155, 223, 0};
		gridBagLayout.rowHeights = new int[]{65, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setAlignmentX(5.0f);
		gbc_editorPane_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_editorPane_1_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_editorPane_1_1.gridx = 1;
		gbc_editorPane_1_1.gridy = 1;
		frame.getContentPane().add(editorPane, gbc_editorPane_1_1);
		editorPane.setContentType("text/html");
		editorPane.setText("<h1>Agregar Foto</h1><p>Comparte una foto con tus seguidores. <br> Puedes arrastrar el fichero aquí. </p>");
		editorPane.setEditable(false);
		editorPane.setDropTarget(new DropTarget() {
		public synchronized void drop(DropTargetDropEvent evt) {
			try {
				evt.acceptDrop(DnDConstants.ACTION_COPY);
				@SuppressWarnings("unchecked")
				List<File> droppedFiles = (List<File>)
				evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
				for (File file : droppedFiles) {
					System.out.println(file.getPath()); }
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		 		}
		 });
		
		JButton btnNewButton = new JButton("Selecciona una foto de tu ordenador");
		btnNewButton.addActionListener(ev -> {
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(frame.getContentPane());
			File currentFile = chooser.getSelectedFile();
			copyFile(currentFile);
			VentanaAddFotoComentario vA = new VentanaAddFotoComentario("/images/" + currentFile.getName());
			vA.setVisible(true);
			frame.dispose();
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 3;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
	}

	private void copyFile(File currentFile) {
		// TODO Auto-generated method stub
		//String filePath = "/images/"+currentFile.getName();
		//File 
		//Files.copy(currentFile.getPath());
	}

}
