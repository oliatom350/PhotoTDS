package tds.PhotoTDS.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import umu.tds.fotos.Foto;
import umu.tds.fotos.Fotos;

@SuppressWarnings("serial")
public class VentanaXMLFotos extends JFrame {

	private static Fotos listFotos;
	private static final int altof = 100;
	private static final int anchof = 70;
	private JScrollPane panel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaXMLFotos frame = new VentanaXMLFotos(listFotos);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaXMLFotos(Fotos fotos) {
		listFotos = fotos;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		panel = new JScrollPane();
		panel.setBackground(Color.WHITE);
		add(panel);
		cargarMatrizFotos();
	}
	
	private static ListCellRenderer<? super Foto> createListRenderer() {
		return new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value,
														int index, boolean isSelected,
														boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (c instanceof JLabel) {
					JLabel label = (JLabel) c;
					Foto foto = (Foto) value;
					label.setText("");
					label.setIcon(new ImageIcon((new ImageIcon(System.getProperty("user.dir") + foto.getPath()).getImage().getScaledInstance(altof, anchof, java.awt.Image.SCALE_SMOOTH))));
				}
				return c;
			}
		 }; 
	}
	
	private void cargarMatrizFotos() {
		JList<Foto> listafotos = new JList<Foto>();
		DefaultListModel<Foto> fotoslistModel = new DefaultListModel<Foto>();
		listafotos.setModel(fotoslistModel);
		listFotos.getFoto().stream().forEach(f -> fotoslistModel.addElement(f));
		listafotos.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listafotos.setVisibleRowCount(-1);
		listafotos.ensureIndexIsVisible(getHeight());
		listafotos.setCellRenderer(createListRenderer());
		panel.setViewportView(listafotos);
	}

}
